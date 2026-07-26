package co.gov.antioquia.civitas.civitas_os.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.gov.antioquia.civitas.civitas_os.dto.request.TicketRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.TicketResponse;
import co.gov.antioquia.civitas.civitas_os.enums.TicketStatus;
import co.gov.antioquia.civitas.civitas_os.response.ApiResponse;
import co.gov.antioquia.civitas.civitas_os.service.TicketService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<ApiResponse<TicketResponse>> createTicket(@Valid @RequestBody TicketRequest request,
            Principal principal) {
        var username = principal.getName();
        var responseDto = ticketService.createTicket(request, username);

        ApiResponse<TicketResponse> apiResponse = ApiResponse.<TicketResponse>builder()
                .status(201)
                .message("Ticket creado exitosamente")
                .data(responseDto)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCTIONARY')")
    public ResponseEntity<ApiResponse<List<TicketResponse>>> getAllTickets() {
        List<TicketResponse> tickets = ticketService.getAllTickets();

        ApiResponse<List<TicketResponse>> apiResponse = ApiResponse.<List<TicketResponse>>builder()
                .status(200)
                .message("Listado de todos los tickets recupareados correctamente")
                .data(tickets)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/my-tickets")
    public ResponseEntity<ApiResponse<List<TicketResponse>>> getMyTickets(Principal principal) {
        List<TicketResponse> tickets = ticketService.getTicketByUsername(principal.getName());

        ApiResponse<List<TicketResponse>> apiResponse = ApiResponse.<List<TicketResponse>>builder()
                .status(200)
                .message("Tus tickets han sido recuperados correctamente")
                .data(tickets)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCTIONARY')")
    public ResponseEntity<ApiResponse<TicketResponse>> updateTicketStatus(@PathVariable Long id,
            @RequestParam TicketStatus status) {
        var responseDto = ticketService.updateStatus(id, status);

        ApiResponse<TicketResponse> apiResponse = ApiResponse.<TicketResponse>builder()
                .status(200)
                .message("Estado del ticket actualizado correctamente")
                .data(responseDto)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

}
