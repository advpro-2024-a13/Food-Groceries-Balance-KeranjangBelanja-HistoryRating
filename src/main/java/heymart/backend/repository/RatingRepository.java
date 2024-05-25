package heymart.backend.repository;

import heymart.backend.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<List<Rating>> findByOwnerId(Long ownerId);
    Optional<List<Rating>> findByMarketId(Long marketId);
}
