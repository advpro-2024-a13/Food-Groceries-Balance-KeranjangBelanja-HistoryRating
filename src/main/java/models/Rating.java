package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Table(name = "rating",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"owner_id", "market_id"})
        })
@Entity
public class Rating {
    private Long ownerId;
    private Long marketId;
    private int score;
    private String review;
    @Id
    private Long id;

    public Rating() {
    }

    public Rating(Long ownerId, Long marketId, int score, String review) {
        this.ownerId = ownerId;
        this.marketId = marketId;
        this.score = score;
        this.review = review;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setRating(int score) {
        this.score = score;
    }

    public int getRating() {
        return score;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

}
