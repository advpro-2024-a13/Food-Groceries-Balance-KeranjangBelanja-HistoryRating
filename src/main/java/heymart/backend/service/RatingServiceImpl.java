package heymart.backend.service;

import heymart.backend.models.Rating;
import heymart.backend.repository.RatingRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating modifyRating(Long id, int rating, String review) {
        Optional<Rating> ratingObj = ratingRepository.findById(id);

        if (ratingObj.isPresent()) {
            Rating r = ratingObj.get();
            r.setScore(rating);
            r.setReview(review);
            return ratingRepository.save(r);
        }
        return null;
    }

    @Override
    public Rating getRatingById(Long id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            return rating.get();
        } else {
            throw new RuntimeException("Rating not found with id: " + id);
        }
    }

    @Override
    public Rating addNewRating(Long ownerId, Long marketId, int rating, String review) {
        Rating newRating = new Rating(ownerId, marketId, rating, review);
        return ratingRepository.save(newRating);
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return ratingRepository.existsById(id);
    }

    @Override
    public CompletableFuture<List<Rating>> getAllRatings() {
        return CompletableFuture.supplyAsync(ratingRepository::findAll);
    }

    @Async
    @Override
    public CompletableFuture<List<Rating>> findByOwnerId(Long ownerId) {
        return CompletableFuture.completedFuture(ratingRepository.findByOwnerId(ownerId).orElse(null));
    }

    @Async
    @Override
    public CompletableFuture<List<Rating>> findBySupermarketId(Long marketId) {
        return CompletableFuture.completedFuture(ratingRepository.findByMarketId(marketId).orElse(null));
    }
}
