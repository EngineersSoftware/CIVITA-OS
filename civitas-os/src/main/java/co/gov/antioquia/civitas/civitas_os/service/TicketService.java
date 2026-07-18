package co.gov.antioquia.civitas.civitas_os.service;

import java.util.List;

import co.gov.antioquia.civitas.civitas_os.dto.request.TicketRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.TicketResponse;
import co.gov.antioquia.civitas.civitas_os.enums.TicketStatus;

public interface TicketService {

    TicketResponse createTicket(TicketRequest request, String username);
    List<TicketResponse> getAllTickets();
    List<TicketResponse> getTicketsByUsername(String username);
    TicketResponse updateTicket(Long id, TicketStatus request);

}
