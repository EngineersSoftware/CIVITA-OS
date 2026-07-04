package co.gov.antioquia.civitas.civitas_os.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.antioquia.civitas.civitas_os.dto.request.LoginRequest;
import co.gov.antioquia.civitas.civitas_os.dto.request.UserRegistrationRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.AuthResponse;
import co.gov.antioquia.civitas.civitas_os.dto.response.UserResponse;
import co.gov.antioquia.civitas.civitas_os.service.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegistrationRequest request){
        var response = authService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

}
