package heymart.backend.service;

import org.springframework.stereotype.Service;

import heymart.backend.models.Rating;
import heymart.backend.repository.RatingRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating modifyRating(Long ownerId, Long marketId, int rating, String review) {
        Optional<List<Rating>> ratingObj = ratingRepository.findByOwnerId(ownerId);
        if (ratingObj.isPresent()) {
            for (Rating r : ratingObj.get()) {
                if (Objects.equals(r.getMarketId(), marketId)) {
                    r.setScore(rating);
                    r.setReview(review);
                    return ratingRepository.save(r);
                }
            }
        }
        return null;
    }

    @Override
    public Rating getRatingById(Long id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.orElse(null);
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

}
