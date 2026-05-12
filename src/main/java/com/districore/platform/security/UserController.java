package com.districore.platform.security;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('COMPANY_ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User created")
                .data(new UserResponse(user.getId(), user.getUsername(), user.getFullName(), user.isEnabled()))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> listUsers() {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Users retrieved")
                .data(userService.listUsers().stream().map(user -> new UserResponse(user.getId(), user.getUsername(), user.getFullName(), user.isEnabled())).collect(Collectors.toList()))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable UUID id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User retrieved")
                .data(new UserResponse(user.getId(), user.getUsername(), user.getFullName(), user.isEnabled()))
                .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('COMPANY_ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequest request) {
        User user = userService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User updated")
                .data(new UserResponse(user.getId(), user.getUsername(), user.getFullName(), user.isEnabled()))
                .build());
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('COMPANY_ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> updateStatus(@PathVariable UUID id, @RequestBody StatusUpdateRequest request) {
        User user = userService.updateStatus(id, request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User status updated")
                .data(new UserResponse(user.getId(), user.getUsername(), user.getFullName(), user.isEnabled()))
                .build());
    }

    @PostMapping("/{id}/roles")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('COMPANY_ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> addRoles(@PathVariable UUID id, @Valid @RequestBody UserRolesRequest request) {
        User user = userService.addRoles(id, request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .success(true)
                .message("Roles added")
                .data(new UserResponse(user.getId(), user.getUsername(), user.getFullName(), user.isEnabled()))
                .build());
    }
}
