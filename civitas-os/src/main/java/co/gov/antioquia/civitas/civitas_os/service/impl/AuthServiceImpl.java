package co.gov.antioquia.civitas.civitas_os.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.gov.antioquia.civitas.civitas_os.dto.request.UserRegistrationRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.UserResponse;
import co.gov.antioquia.civitas.civitas_os.entity.User;
import co.gov.antioquia.civitas.civitas_os.repository.UserRepository;
import co.gov.antioquia.civitas.civitas_os.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse registerUser(UserRegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }

        var user = User.builder()
                       .username(request.getUsername())
                       .email(request.getEmail())
                       .password(passwordEncoder.encode(request.getPassword()))
                       .enabled(true)
                       .build();

        var savedUser = userRepository.save(user);

        return UserResponse.builder()
                            .id(savedUser.getId())
                            .username(savedUser.getUsername())
                            .email(savedUser.getEmail())
                            .build();
    }

}
