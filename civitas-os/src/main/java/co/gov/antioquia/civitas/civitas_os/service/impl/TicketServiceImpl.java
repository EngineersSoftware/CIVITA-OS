package co.gov.antioquia.civitas.civitas_os.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.gov.antioquia.civitas.civitas_os.dto.request.TicketRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.TicketResponse;
import co.gov.antioquia.civitas.civitas_os.enums.TicketStatus;
import co.gov.antioquia.civitas.civitas_os.exception.ResourceNotFoundException;
import co.gov.antioquia.civitas.civitas_os.mapper.TicketMapper;
import co.gov.antioquia.civitas.civitas_os.repository.TicketRepository;
import co.gov.antioquia.civitas.civitas_os.repository.UserRepository;
import co.gov.antioquia.civitas.civitas_os.service.TicketService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;

    @Override
    @Transactional
    public TicketResponse createTicket(TicketRequest request, String username) {
        var creator = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        var ticket = ticketMapper.toEntity(request);

        ticket.setCreator(creator);

        var savedTicket = ticketRepository.save(ticket);

        return ticketMapper.toResponse(savedTicket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> getTicketByUsername(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found" + username));

        return ticketRepository.findByCreatorId(user.getId()).stream()
                .map(ticketMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TicketResponse updateStatus(Long id, TicketStatus status) {
        var ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found id: " + id));

        ticket.setStatus(status);
        var updatedTicket = ticketRepository.save(ticket);

        return ticketMapper.toResponse(updatedTicket);
    }
}
