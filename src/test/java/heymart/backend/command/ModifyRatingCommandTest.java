package heymart.backend.command;

import heymart.backend.models.Rating;
import heymart.backend.service.RatingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ModifyRatingCommandTest {

    @Test
    void testModifyRatingCommand_HappyPath() {
        RatingService ratingService = Mockito.mock(RatingService.class);
        Rating rating = new Rating(1L, 1L, 4, "Good");
        when(ratingService.modifyRating(any(Long.class), any(Integer.class), any(String.class))).thenReturn(rating);

        ModifyRatingCommand command = new ModifyRatingCommand(ratingService, 1L, 4, "Good");
        command.execute();

        verify(ratingService, times(1)).modifyRating(1L, 4, "Good");
    }

    @Test
    void testModifyRatingCommand_UnhappyPath() {
        RatingService ratingService = Mockito.mock(RatingService.class);
        when(ratingService.modifyRating(any(Long.class), any(Integer.class), any(String.class))).thenReturn(null);

        ModifyRatingCommand command = new ModifyRatingCommand(ratingService, 1L, 4, "Good");
        command.execute();

        verify(ratingService, times(1)).modifyRating(1L, 4, "Good");
    }
}
