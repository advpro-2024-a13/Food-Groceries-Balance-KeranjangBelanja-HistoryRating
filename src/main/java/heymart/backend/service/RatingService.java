package heymart.backend.service;

import heymart.backend.models.Rating;

public interface RatingService {
    Rating modifyRating(Long ownerId, Long marketId, int score, String review);
    Rating getRatingById(Long id);
    Rating addNewRating(Long ownerId, Long marketId, int score, String review);
    void deleteRating(Long id);
    boolean existsById(Long id);

}
