package heymart.backend.service;

import heymart.backend.models.Rating;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RatingService {
    Rating modifyRating(Long id, int rating, String review);
    Rating getRatingById(Long id);
    Rating addNewRating(Long ownerId, Long marketId, int rating, String review);
    void deleteRating(Long id);
    boolean existsById(Long id);
    CompletableFuture<List<Rating>> getAllRatings();
    CompletableFuture<List<Rating>> findByOwnerId(Long ownerId);
    CompletableFuture<List<Rating>> findByMarketId(Long marketId);
}
