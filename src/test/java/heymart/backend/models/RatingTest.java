package heymart.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingTest {

    private Rating rating;

    @BeforeEach
    public void setUp() {
        rating = new Rating();
    }

    @Test
    public void testGetSetOwnerId() {
        Long ownerId = 123L;
        rating.setOwnerId(ownerId);
        assertEquals(ownerId, rating.getOwnerId());
    }

    @Test
    public void testGetSetMarketId() {
        Long marketId = 456L;
        rating.setMarketId(marketId);
        assertEquals(marketId, rating.getMarketId());
    }

    @Test
    public void testGetSetScore() {
        int score = 10;
        rating.setScore(score);
        assertEquals(score, rating.getScore());
    }

    @Test
    public void testGetSetReview() {
        String review = "Good";
        rating.setReview(review);
        assertEquals(review, rating.getReview());
    }

    @Test
    public void testConstructor() {
        Long ownerId = 123L;
        Long marketId = 456L;
        int score = 10;
        String review = "Good";
        Rating rating = new Rating(ownerId, marketId, score, review);
        assertEquals(ownerId, rating.getOwnerId());
        assertEquals(marketId, rating.getMarketId());
        assertEquals(score, rating.getScore());
        assertEquals(review, rating.getReview());
    }

    @Test
    public void testSetId() {
        Long id = 789L;
        rating.setId(id);
        assertEquals(id, rating.getId());
    }
}