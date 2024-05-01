package heymart.backend.repository;

import heymart.backend.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    public Optional<List<Rating>> findByOwnerId(Long ownerId);
    public Optional<List<Rating>> findByMarketId(Long marketId);
}