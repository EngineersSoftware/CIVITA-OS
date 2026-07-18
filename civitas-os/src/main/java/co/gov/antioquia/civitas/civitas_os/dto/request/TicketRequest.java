package co.gov.antioquia.civitas.civitas_os.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TicketRequest(
    @NotBlank(message = "El titulo es requerido")
    @Size(min = 5, max = 100, message = "El titulo debe tener entre 5 y 100 caracteres")
    String title,

    @NotBlank(message = "La descripcion es requerida")
    @Size(min = 10, message = "La descripcion debe tener al menos 10 caracteres")
    String description
) {

}
