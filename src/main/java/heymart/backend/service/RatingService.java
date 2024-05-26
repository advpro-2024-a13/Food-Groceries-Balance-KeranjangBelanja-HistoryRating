package heymart.backend.service;

import heymart.backend.models.Rating;

public interface RatingService {
    public Rating modifyRating(Long ownerId, Long marketId, int score, String review);
    public Rating getRatingById(Long id);
    public Rating addNewRating(Long ownerId, Long marketId, int score, String review);
    public void deleteRating(Long id);
    public boolean existsById(Long id);

}
