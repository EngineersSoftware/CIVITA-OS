package co.gov.antioquia.civitas.civitas_os.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.antioquia.civitas.civitas_os.dto.request.TicketRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.TicketResponse;
import co.gov.antioquia.civitas.civitas_os.entity.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    Ticket toEntity(TicketRequest request);

    @Mapping(source = "creator.username", target = "creatorUsername")
    TicketResponse toResponse(Ticket ticket);

}
