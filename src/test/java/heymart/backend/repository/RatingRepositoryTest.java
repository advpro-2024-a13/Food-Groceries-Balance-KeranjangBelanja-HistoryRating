package heymart.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import heymart.backend.models.Rating;
import heymart.backend.service.RatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RatingRepositoryTest {

    @Mock
    private RatingRepository ratingRepository;

    private List<Rating> ratings;

    @BeforeEach
    public void setUp() {
        ratings = new ArrayList<>();
        ratings.add(new Rating(1L, 1L, 4, "Great product!"));
        ratings.add(new Rating(1L, 2L, 3, "Good product."));
        ratings.add(new Rating(2L, 1L, 5, "Excellent product!"));
    }

    @Test
    public void testFindByOwnerId() {
        Long ownerId = 1L;
        List<Rating> expectedRatings = new ArrayList<>();
        expectedRatings.add(ratings.get(0));
        expectedRatings.add(ratings.get(1));

        when(ratingRepository.findByOwnerId(ownerId)).thenReturn(Optional.of(expectedRatings));

        Optional<List<Rating>> foundRatings = ratingRepository.findByOwnerId(ownerId);

        assertEquals(expectedRatings, foundRatings.get());
    }

    @Test
    public void testFindByMarketId() {
        Long marketId = 1L;
        List<Rating> expectedRatings = new ArrayList<>();
        expectedRatings.add(ratings.get(0));
        expectedRatings.add(ratings.get(2));

        when(ratingRepository.findByMarketId(marketId)).thenReturn(Optional.of(expectedRatings));

        Optional<List<Rating>> foundRatings = ratingRepository.findByMarketId(marketId);

        assertEquals(expectedRatings, foundRatings.get());

    @Test
    public void testFindByOwnerId() {
        Rating rating = new Rating();
        rating.setOwnerId(123L);
        rating.setMarketId(456L);
        rating.setScore(5);
        rating.setReview("Good");

        when(ratingRepository.findByOwnerId(123L)).thenReturn(Optional.of(List.of(rating)));

        Optional<List<Rating>> foundRating = ratingRepository.findByOwnerId(123L);

        assertTrue(foundRating.isPresent());
        assertEquals(123L, foundRating.get().getFirst().getOwnerId());
        assertEquals(456L, foundRating.get().getFirst().getMarketId());
        assertEquals(5, foundRating.get().getFirst().getScore());
        assertEquals("Good", foundRating.get().getFirst().getReview());
    }

    @Test
    public void testFindByMarketId() {
        Rating rating = new Rating();
        rating.setOwnerId(123L);
        rating.setMarketId(456L);
        rating.setScore(5);
        rating.setReview("Good");

        when(ratingRepository.findByMarketId(456L)).thenReturn(Optional.of(List.of(rating)));

        Optional<List<Rating>> foundRating = ratingRepository.findByMarketId(456L);

        assertTrue(foundRating.isPresent());
        assertEquals(123L, foundRating.get().getFirst().getOwnerId());
        assertEquals(456L, foundRating.get().getFirst().getMarketId());
        assertEquals(5, foundRating.get().getFirst().getScore());
        assertEquals("Good", foundRating.get().getFirst().getReview());
    }
}