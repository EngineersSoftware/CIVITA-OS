package co.gov.antioquia.civitas.civitas_os.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import co.gov.antioquia.civitas.civitas_os.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long>, JpaSpecificationExecutor<Asset> {

    Optional<Asset> findByCode(String code);
    boolean existsByCode(String code);

}
