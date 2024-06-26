package heymart.backend.repository;

import heymart.backend.models.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistRepository extends JpaRepository<History, Long> {
    Optional<List<History>> findByOwnerId(Long ownerId);
    Optional<List<History>> findByMarketId(Long marketId);
}
