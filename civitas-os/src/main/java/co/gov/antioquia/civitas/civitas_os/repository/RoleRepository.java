package co.gov.antioquia.civitas.civitas_os.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.gov.antioquia.civitas.civitas_os.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
