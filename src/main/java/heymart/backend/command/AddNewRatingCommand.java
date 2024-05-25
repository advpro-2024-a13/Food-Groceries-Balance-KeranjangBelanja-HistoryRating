package heymart.backend.command;

import heymart.backend.service.RatingService;

public class AddNewRatingCommand implements Command {
    private final RatingService ratingService;
    private final Long ownerId;
    private final Long marketId;
    private final int rating;
    private final String review;

    public AddNewRatingCommand(RatingService ratingService, Long ownerId, Long marketId, int rating, String review) {
        this.ratingService = ratingService;
        this.ownerId = ownerId;
        this.marketId = marketId;
        this.rating = rating;
        this.review = review;
    }

    @Override
    public void execute() {
        ratingService.addNewRating(ownerId, marketId, rating, review);
    }
}
