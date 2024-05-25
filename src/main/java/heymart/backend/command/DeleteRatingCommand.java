package heymart.backend.command;

import heymart.backend.service.RatingService;

public class DeleteRatingCommand implements Command {
    private final RatingService ratingService;
    private final Long id;

    public DeleteRatingCommand(RatingService ratingService, Long id) {
        this.ratingService = ratingService;
        this.id = id;
    }

    @Override
    public void execute() {
        ratingService.deleteRating(id);
    }
}
