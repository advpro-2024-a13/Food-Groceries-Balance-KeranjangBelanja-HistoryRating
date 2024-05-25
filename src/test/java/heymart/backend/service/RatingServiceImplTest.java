package heymart.backend.service;

import heymart.backend.models.Rating;
import heymart.backend.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testModifyRating() {
        Long id = 1L;
        int rating = 4;
        String review = "Great product!";
        Rating existingRating = new Rating(123L, 234L, 3, "Good product");

        when(ratingRepository.findById(id)).thenReturn(Optional.of(existingRating));
        when(ratingRepository.save(existingRating)).thenReturn(existingRating);

        Rating modifiedRating = ratingService.modifyRating(id, rating, review);

        assertEquals(rating, modifiedRating.getScore());
        assertEquals(review, modifiedRating.getReview());
    }

    @Test
    void testGetRatingById() {
        Long ratingId = 789L;
        Rating rating = new Rating(123L, 456L, 4, "Great product!");

        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));

        Rating retrievedRating = ratingService.getRatingById(ratingId);

        assertEquals(rating, retrievedRating);
    }

    @Test
    void testAddNewRating() {
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
    void testDeleteRating() {
        Long ratingId = 789L;

        ratingService.deleteRating(ratingId);

        verify(ratingRepository).deleteById(ratingId);
    }

    @Test
    void testExistsById() {
        Long ratingId = 789L;

        when(ratingRepository.existsById(ratingId)).thenReturn(true);

        boolean exists = ratingService.existsById(ratingId);

        assertTrue(exists);
    }
}