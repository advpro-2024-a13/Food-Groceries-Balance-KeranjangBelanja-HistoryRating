package heymart.backend.models;

import org.junit.jupiter.api.Test;

public class RatingTest {
    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.mockito.Mockito.mock;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import heymart.backend.models.RatingTest;

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
        public void testGetSetRatingScore() {
            int ratingScore = 10;
            rating.setRating(ratingScore);
            assertEquals(ratingScore, rating.getRating());
        }
        @Test
        public void testGetSetRatingReview() {
            String ratingReview = "Good";
            rating.setReview(ratingReview);
            assertEquals(ratingReview, rating.getReview());
        }
        @Test
        public void testConstructor() {
            Long ownerId = 123L;
            Long marketId = 456L;
            int ratingScore = 10;
            String ratingReview = "Good";
            Rating rating = new Rating(ownerId, marketId, ratingScore, ratingReview);
            assertEquals(ownerId, rating.getOwnerId());
            assertEquals(marketId, rating.getMarketId());
            assertEquals(ratingScore, rating.getRating());
            assertEquals(ratingReview, rating.getReview());
        }
    }
}
