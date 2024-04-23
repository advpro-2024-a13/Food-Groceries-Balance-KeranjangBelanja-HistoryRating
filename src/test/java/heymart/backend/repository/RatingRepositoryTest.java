package heymart.backend.repository;

import heymart.backend.models.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RatingRepositoryTest {
    private RatingRepository ratingRepository;

    List<Rating> ratings;

    @BeforeEach
    void setUp() {
        ratingRepository = new RatingRepository();

        ratings = new ArrayList<>();

        Rating rating1 = new Rating(1L, 1L, 4, "Great service!");
        ratings.add(rating1);
        Rating rating2 = new Rating(2L, 1L, 3, "Average experience.");
        ratings.add(rating2);
        Rating rating3 = new Rating(3L, 2L, 5, "Excellent products!");
        ratings.add(rating3);
        Rating rating4 = new Rating(4L, 2L, 2, "Poor customer support.");
        ratings.add(rating4);
    }

    @Test
    void testSaveCreate() {
        Rating newRating = new Rating(5L, 3L, 4, "Good value for money.");
        ratingRepository.save(newRating);
        List<Rating> allRatings = ratingRepository.findAll();
        assertTrue(allRatings.contains(newRating));
    }

    @Test
    void testSaveUpdate() {
        Rating existingRating = ratings.get(0);
        existingRating.setReview("Updated review.");
        ratingRepository.save(existingRating);
        Rating updatedRating = ratingRepository.findById(existingRating.getId());
        assertEquals("Updated review.", updatedRating.getReview());
    }

    @Test
    void findByOwnerId() {
        List<Rating> ratingsForOwner1 = ratingRepository.findByOwnerId(1L);
        assertEquals(1, ratingsForOwner1.size());
        assertTrue(ratingsForOwner1.contains(ratings.get(0)));
    }

    @Test
    void findByMarketId() {
        List<Rating> ratingsForMarket1 = ratingRepository.findByMarketId(1L);
        assertEquals(2, ratingsForMarket1.size());
        assertTrue(ratingsForMarket1.contains(ratings.get(0)));
        assertTrue(ratingsForMarket1.contains(ratings.get(1)));
    }

    @Test
    void testDelete() {
        Rating ratingToDelete = ratings.get(0);
        ratingRepository.delete(ratingToDelete);
        List<Rating> allRatings = ratingRepository.findAll();
        assertFalse(allRatings.contains(ratingToDelete));
    }

    @Test
    void testFindAll() {
        List<Rating> allRatings = ratingRepository.findAll();
        assertEquals(ratings.size(), allRatings.size());
        assertTrue(allRatings.containsAll(ratings));
    }
}