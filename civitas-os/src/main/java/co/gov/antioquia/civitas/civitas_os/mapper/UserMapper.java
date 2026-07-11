package co.gov.antioquia.civitas.civitas_os.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.gov.antioquia.civitas.civitas_os.dto.request.UserRegistrationRequest;
import co.gov.antioquia.civitas.civitas_os.dto.response.UserResponse;
import co.gov.antioquia.civitas.civitas_os.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "password", ignore = true)
    User toEntity(UserRegistrationRequest Request);

    UserResponse toResponse(User user);

}
