package co.gov.antioquia.civitas.civitas_os.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.antioquia.civitas.civitas_os.dto.request.UserRegistrationRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.UserDtoResponse;
import co.gov.antioquia.civitas.civitas_os.response.ApiResponse;
import co.gov.antioquia.civitas.civitas_os.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDtoResponse>> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        var responseDto = userService.createUser(request);
        ApiResponse<UserDtoResponse> apiResponse = ApiResponse.<UserDtoResponse>builder()
                .status(201)
                .message("Usuario registrado exitosamente")
                .data(responseDto)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDtoResponse>> getUserById(@PathVariable Long id) {
        var responseDto = userService.getUserById(id);
        ApiResponse<UserDtoResponse> apiResponse = ApiResponse.<UserDtoResponse>builder()
                .status(200)
                .message("Usuario encontrado exitosamente")
                .data(responseDto)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
