package co.gov.antioquia.civitas.civitas_os.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.gov.antioquia.civitas.civitas_os.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
