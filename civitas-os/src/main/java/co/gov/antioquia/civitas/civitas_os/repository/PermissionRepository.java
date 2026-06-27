package co.gov.antioquia.civitas.civitas_os.repository;

import java.security.Permission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
