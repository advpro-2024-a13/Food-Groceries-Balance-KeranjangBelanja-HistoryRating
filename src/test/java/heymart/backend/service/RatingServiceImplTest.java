package heymart.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import heymart.backend.models.Rating;
import heymart.backend.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testModifyRating() {
        Long ownerId = 123L;
        Long marketId = 456L;
        int rating = 4;
        String review = "Great product!";
        Rating existingRating = new Rating(ownerId, marketId, 3, "Good product");
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(existingRating);

        when(ratingRepository.findByOwnerId(ownerId)).thenReturn(Optional.of(ratingList));
        when(ratingRepository.save(existingRating)).thenReturn(existingRating);

        Rating modifiedRating = ratingService.modifyRating(ownerId, marketId, rating, review);

        assertEquals(rating, modifiedRating.getScore());
        assertEquals(review, modifiedRating.getReview());
    }

    @Test
    public void testGetRatingById() {
        Long ratingId = 789L;
        Rating rating = new Rating(123L, 456L, 4, "Great product!");

        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));

        Rating retrievedRating = ratingService.getRatingById(ratingId);

        assertEquals(rating, retrievedRating);
    }

    @Test
    public void testAddNewRating() {
        Long ownerId = 123L;
        Long marketId = 456L;
        int rating = 4;
        String review = "Great product!";
        Rating newRating = new Rating(ownerId, marketId, rating, review);

        when(ratingRepository.save(any(Rating.class))).thenReturn(newRating);

        Rating addedRating = ratingService.addNewRating(ownerId, marketId, rating, review);

        assertEquals(newRating, addedRating);
    }

    @Test
    public void testDeleteRating() {
        Long ratingId = 789L;

        ratingService.deleteRating(ratingId);

        verify(ratingRepository).deleteById(ratingId);
    }

    @Test
    public void testExistsById() {
        Long ratingId = 789L;

        when(ratingRepository.existsById(ratingId)).thenReturn(true);

        boolean exists = ratingService.existsById(ratingId);

        assertTrue(exists);
    }
}