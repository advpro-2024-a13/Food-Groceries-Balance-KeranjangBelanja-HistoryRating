package heymart.backend.repository;

import heymart.backend.models.KeranjangBelanja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeranjangBelanjaRepository extends JpaRepository<KeranjangBelanja, Long>{
    Optional<KeranjangBelanja> findKeranjangBelanjaById(Long keranjangBelanjaId);
}