package heymart.backend.repository;

import heymart.backend.models.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingRepositoryTest {

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
    void testFindByOwnerId() {
        Long ownerId = 1L;
        List<Rating> expectedRatings = new ArrayList<>();
        expectedRatings.add(ratings.get(0));
        expectedRatings.add(ratings.get(1));

        when(ratingRepository.findByOwnerId(ownerId)).thenReturn(Optional.of(expectedRatings));

        Optional<List<Rating>> foundRatings = ratingRepository.findByOwnerId(ownerId);

        assertTrue(foundRatings.isPresent());
        assertEquals(expectedRatings, foundRatings.get());
    }

    @Test
    void testFindByMarketId() {
        Long marketId = 1L;
        List<Rating> expectedRatings = new ArrayList<>();
        expectedRatings.add(ratings.get(0));
        expectedRatings.add(ratings.get(2));

        when(ratingRepository.findByMarketId(marketId)).thenReturn(Optional.of(expectedRatings));

        Optional<List<Rating>> foundRatings = ratingRepository.findByMarketId(marketId);

        assertTrue(foundRatings.isPresent());
        assertEquals(expectedRatings, foundRatings.get());
    }
}