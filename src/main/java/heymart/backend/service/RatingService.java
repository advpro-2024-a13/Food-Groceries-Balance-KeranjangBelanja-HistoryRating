package heymart.backend.service;

import heymart.backend.models.Rating;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RatingService {
    public Rating modifyRating(Long id, int score, String review);
    public Rating getRatingById(Long id);
    public Rating addNewRating(Long ownerId, Long marketId, int score, String review);
    public void deleteRating(Long id);
    public boolean existsById(Long id);
    CompletableFuture<List<Rating>> getAllRatings();
    CompletableFuture<List<Rating>> findByOwnerId(Long ownerId);
    CompletableFuture<List<Rating>> findBySupermarketId(Long marketId);
}
