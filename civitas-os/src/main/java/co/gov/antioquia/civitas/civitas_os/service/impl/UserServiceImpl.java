package co.gov.antioquia.civitas.civitas_os.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.gov.antioquia.civitas.civitas_os.dto.request.UserRegistrationRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.UserDtoResponse;
import co.gov.antioquia.civitas.civitas_os.exception.ResourceNotFoundException;
import co.gov.antioquia.civitas.civitas_os.mapper.UserMapper;
import co.gov.antioquia.civitas.civitas_os.repository.UserRepository;
import co.gov.antioquia.civitas.civitas_os.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;
        private final UserMapper userMapper;

        @Override
        @Transactional
        public UserDtoResponse createUser(UserRegistrationRequest request) {
            if (userRepository.existsByUsername(request.getUsername())) {
                throw new IllegalArgumentException("El nombre de usuario ya esta en uso");
            }

            var userToSave = userMapper.toEntity(request);
            userToSave.setEnabled(true);
            var savedUser = userRepository.save(userToSave);
            return userMapper.toDtoResponse(savedUser);
        }

        @Override
        @Transactional(readOnly = true)
        public UserDtoResponse getUserById(Long id) {
            var user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
            return userMapper.toDtoResponse(user);
        }

}
