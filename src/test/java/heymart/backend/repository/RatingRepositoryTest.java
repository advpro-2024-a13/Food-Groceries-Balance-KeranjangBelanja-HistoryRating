package heymart.backend.repository;

import heymart.backend.models.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RatingRepositoryTest {

    @Mock
    RatingRepository ratingRepository;

    List<Rating> ratings;

    @BeforeEach
    void setUp() {
        ratings = new ArrayList<>();

        Rating rating1 = new Rating(1L, 1L, 5, "Great service!");
        ratings.add(rating1);
        ratingRepository.save(rating1);
        Rating rating2 = new Rating(2L, 1L, 3, "Average experience.");
        ratings.add(rating2);
        ratingRepository.save(rating2);
        Rating rating3 = new Rating(3L, 2L, 5, "Excellent products!");
        ratings.add(rating3);
        ratingRepository.save(rating3);
        Rating rating4 = new Rating(4L, 2L, 2, "Poor customer support.");
        ratings.add(rating4);
        ratingRepository.save(rating4);
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
        Optional<Rating> updatedRating = ratingRepository.findById(existingRating.getId());
        assertTrue(updatedRating.isPresent());
        assertEquals("Updated review.", updatedRating.get().getReview());
    }

    @Test
    void findByOwnerId() {
        Optional<List<Rating>> ratingsForOwner1 = ratingRepository.findByOwnerId(1L);
        assertTrue(ratingsForOwner1.isPresent());
        assertEquals(1, ratingsForOwner1.get().size());
        assertTrue(ratingsForOwner1.get().contains(ratings.getFirst()));
    }

    @Test
    void findByMarketId() {
        Optional<List<Rating>> ratingsForMarket1 = ratingRepository.findByMarketId(1L);
        assertTrue(ratingsForMarket1.isPresent());
        assertEquals(2, ratingsForMarket1.get().size());
        assertTrue(ratingsForMarket1.get().contains(ratings.get(0)));
        assertTrue(ratingsForMarket1.get().contains(ratings.get(1)));
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