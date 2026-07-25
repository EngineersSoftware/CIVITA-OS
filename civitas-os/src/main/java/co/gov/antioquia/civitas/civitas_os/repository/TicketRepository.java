package co.gov.antioquia.civitas.civitas_os.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.gov.antioquia.civitas.civitas_os.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByCreatorId(Long userId);

}
