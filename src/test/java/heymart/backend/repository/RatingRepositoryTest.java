package heymart.backend.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import heymart.backend.models.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RatingRepositoryTest {

    @Mock
    private RatingRepository ratingRepository;

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