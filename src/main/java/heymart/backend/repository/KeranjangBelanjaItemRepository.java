package heymart.backend.repository;

import heymart.backend.models.KeranjangBelanjaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface KeranjangBelanjaItemRepository extends JpaRepository<KeranjangBelanjaItem, UUID> {
    Optional<KeranjangBelanjaItem> findByProductId(UUID productId);
}