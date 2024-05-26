package heymart.backend.command;

import heymart.backend.service.RatingService;

public class ModifyRatingCommand implements Command {
    private final RatingService ratingService;
    private final Long id;
    private final int rating;
    private final String review;

    public ModifyRatingCommand(RatingService ratingService, Long id, int rating, String review) {
        this.ratingService = ratingService;
        this.id = id;
        this.rating = rating;
        this.review = review;
    }

    @Override
    public void execute() {
        ratingService.modifyRating(id, rating, review);
    }
}
