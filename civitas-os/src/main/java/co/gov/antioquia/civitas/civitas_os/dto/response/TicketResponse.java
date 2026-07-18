package co.gov.antioquia.civitas.civitas_os.dto.response;

import java.time.LocalDateTime;

import co.gov.antioquia.civitas.civitas_os.enums.TicketStatus;

public record TicketResponse(

    Long id,
    String title,
    String description,
    TicketStatus status,
    String creatorUsername,
    LocalDateTime createdAt
) {

}
