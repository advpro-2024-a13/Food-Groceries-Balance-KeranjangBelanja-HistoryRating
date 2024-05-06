package heymart.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heymart.backend.models.Rating;
import heymart.backend.repository.RatingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating modifyRating(Long ownerId, Long marketId, int rating, String review) {
        Optional<List<Rating>> ratingObj = ratingRepository.findByOwnerId(ownerId);
        for (Rating r : ratingObj.get()) {
            if (r.getMarketId() == marketId) {
                r.setScore(rating);
                r.setReview(review);
                return ratingRepository.save(r);
            }
        }
        return null;
    }
    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).get();
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
