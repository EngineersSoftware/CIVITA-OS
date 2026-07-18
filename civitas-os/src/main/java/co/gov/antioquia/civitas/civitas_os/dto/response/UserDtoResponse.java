package co.gov.antioquia.civitas.civitas_os.dto.response;

import java.time.LocalDateTime;

public record UserDtoResponse(
    Long id,
    String username,
    String email,
    boolean enabled,
    LocalDateTime createdAt
) {

}
