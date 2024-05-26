package heymart.backend.command;

import heymart.backend.models.Rating;
import heymart.backend.service.RatingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddNewRatingCommandTest {

    @Test
    void testAddNewRatingCommand_HappyPath() {
        RatingService ratingService = Mockito.mock(RatingService.class);
        Rating rating = new Rating(1L, 1L, 5, "Great");
        when(ratingService.addNewRating(any(Long.class), any(Long.class), any(Integer.class), any(String.class))).thenReturn(rating);

        AddNewRatingCommand command = new AddNewRatingCommand(ratingService, 1L, 1L, 5, "Great");
        command.execute();

        verify(ratingService, times(1)).addNewRating(1L, 1L, 5, "Great");
    }

    @Test
    void testAddNewRatingCommand_UnhappyPath() {
        RatingService ratingService = Mockito.mock(RatingService.class);
        when(ratingService.addNewRating(any(Long.class), any(Long.class), any(Integer.class), any(String.class))).thenThrow(new IllegalArgumentException("Invalid rating"));

        AddNewRatingCommand command = new AddNewRatingCommand(ratingService, 1L, 1L, 6, "Invalid");

        try {
            command.execute();
        } catch (Exception e) {
            verify(ratingService, times(1)).addNewRating(1L, 1L, 6, "Invalid");
            assert(e instanceof IllegalArgumentException);
            assert(e.getMessage().equals("Invalid rating"));
        }
    }
}
