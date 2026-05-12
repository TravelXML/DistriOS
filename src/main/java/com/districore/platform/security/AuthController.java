package com.districore.platform.security;

import com.districore.platform.common.ApiResponse;
import com.districore.platform.common.TenantContext;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                          UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return ResponseEntity.ok(ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Login successful")
                .data(new AuthResponse(token))
                .correlationId(TenantContext.getTenantId())
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setEnabled(true);
        user.setTenantId(TenantContext.getTenantId());
        user.setRoles(request.getRoleNames().stream()
                .map(name -> roleRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("Role not found: " + name)))
                .collect(Collectors.toSet()));
        userRepository.save(user);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .success(true)
                .message("Registration successful")
                .data(new UserResponse(user.getId(), user.getUsername(), user.getFullName(), user.isEnabled()))
                .correlationId(TenantContext.getTenantId())
                .build());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        if (!jwtTokenProvider.validateToken(request.getRefreshToken())) {
            throw new IllegalArgumentException("Invalid refresh token");
        }
        String username = jwtTokenProvider.getUsername(request.getRefreshToken());
        User user = userRepository.findByUsername(username).orElseThrow();
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return ResponseEntity.ok(ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Token refreshed")
                .data(new AuthResponse(token))
                .correlationId(TenantContext.getTenantId())
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Logout successful")
                .data("logged out")
                .correlationId(TenantContext.getTenantId())
                .build());
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> me() {
        String username = SecurityUtils.getCurrentUsername();
        User user = userRepository.findByUsername(username).orElseThrow();
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .success(true)
                .message("Current user")
                .data(new UserResponse(user.getId(), user.getUsername(), user.getFullName(), user.isEnabled()))
                .correlationId(TenantContext.getTenantId())
                .build());
    }
}
