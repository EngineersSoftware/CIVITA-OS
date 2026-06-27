package co.gov.antioquia.civitas.civitas_os.service;

import co.gov.antioquia.civitas.civitas_os.dto.request.UserRegistrationRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.UserResponse;

public interface AuthService {

    UserResponse registerUser(UserRegistrationRequest request);

}
