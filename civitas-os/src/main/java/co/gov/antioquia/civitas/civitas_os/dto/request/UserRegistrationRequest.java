package co.gov.antioquia.civitas.civitas_os.dto.request;

import co.gov.antioquia.civitas.civitas_os.constants.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {

    @NotBlank(message = "El usuario es obligatorio")
    @Size(max = ValidationConstants.MAX_USERNAME_LENGTH, message = "El usuario no puede superar los 50 caracteres")
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email invalido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(
        regexp = ValidationConstants.PASSWORD_REGEX, 
        message = ValidationConstants.PASSWORD_INVALID_MSG
    )
    private String password;

}
