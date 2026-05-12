package com.districore.platform.security;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setTenantId(TenantContext.getTenantId());
        user.setRoles(request.getRoleNames().stream()
                .map(name -> roleRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Role not found: " + name)))
                .collect(Collectors.toSet()));
        return userRepository.save(user);
    }

    public List<User> listUsers() {
        return userRepository.findAll().stream()
                .filter(user -> TenantContext.getTenantId().equals(user.getTenantId()))
                .collect(Collectors.toList());
    }

    public User getUser(UUID id) {
        return userRepository.findById(id)
                .filter(user -> TenantContext.getTenantId().equals(user.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User updateUser(UUID id, UpdateUserRequest request) {
        User user = getUser(id);
        user.setFullName(request.getFullName());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return userRepository.save(user);
    }

    public User updateStatus(UUID id, StatusUpdateRequest request) {
        User user = getUser(id);
        user.setEnabled(request.isEnabled());
        return userRepository.save(user);
    }

    public User addRoles(UUID id, UserRolesRequest request) {
        User user = getUser(id);
        user.getRoles().addAll(request.getRoleNames().stream()
                .map(name -> roleRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Role not found: " + name)))
                .collect(Collectors.toSet()));
        return userRepository.save(user);
    }
}
