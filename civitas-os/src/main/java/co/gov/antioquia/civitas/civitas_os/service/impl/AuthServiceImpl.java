package co.gov.antioquia.civitas.civitas_os.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.gov.antioquia.civitas.civitas_os.dto.request.LoginRequest;
import co.gov.antioquia.civitas.civitas_os.dto.request.UserRegistrationRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.AuthResponse;
import co.gov.antioquia.civitas.civitas_os.dto.response.UserResponse;
import co.gov.antioquia.civitas.civitas_os.entity.User;
import co.gov.antioquia.civitas.civitas_os.jwt.JwtService;
import co.gov.antioquia.civitas.civitas_os.mapper.UserMapper;
import co.gov.antioquia.civitas.civitas_os.repository.UserRepository;
import co.gov.antioquia.civitas.civitas_os.service.AuthService;
import co.gov.antioquia.civitas.civitas_os.specification.UserSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public UserResponse registerUser(UserRegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }
        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken, "Bearer");
    }

    public List<UserResponse> searchUsers(String username, String email, Boolean enabled) {
        Specification<User> spec = Specification
                .where(UserSpecification.containUsername(username))
                .and(UserSpecification.hasEmail(email))
                .and(UserSpecification.isEnabled(enabled));
        List<User> users = userRepository.findAll(spec);
        return users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

}