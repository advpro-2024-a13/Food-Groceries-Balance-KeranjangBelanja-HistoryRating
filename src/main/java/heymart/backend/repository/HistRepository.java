package heymart.backend.repository;

import heymart.backend.models.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface HistRepository extends JpaRepository<History, Long> {
    public Optional<List<History>> findByOwnerId(Long ownerId);
    public Optional<List<History>> findByMarketId(Long marketId);
}
