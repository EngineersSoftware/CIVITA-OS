package co.gov.antioquia.civitas.civitas_os.service;

import co.gov.antioquia.civitas.civitas_os.dto.request.UserRegistrationRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.UserDtoResponse;

public interface UserService {

    UserDtoResponse createUser(UserRegistrationRequest request);
    UserDtoResponse getUserById(Long id);

}
