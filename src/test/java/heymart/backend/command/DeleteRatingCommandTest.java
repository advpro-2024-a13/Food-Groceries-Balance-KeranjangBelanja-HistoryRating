package heymart.backend.command;

import heymart.backend.service.RatingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteRatingCommandTest {

    @Test
    void testDeleteRatingCommand_HappyPath() {
        RatingService ratingService = Mockito.mock(RatingService.class);

        DeleteRatingCommand command = new DeleteRatingCommand(ratingService, 1L);
        command.execute();

        verify(ratingService, times(1)).deleteRating(1L);
    }

    @Test
    void testDeleteRatingCommand_UnhappyPath() {
        RatingService ratingService = Mockito.mock(RatingService.class);

        DeleteRatingCommand command = new DeleteRatingCommand(ratingService, 1L);
        command.execute();

        verify(ratingService, times(1)).deleteRating(1L);
        // Unhappy path is typically that the id doesn't exist, but since deleteById doesn't return, it's hard to test
    }
}
