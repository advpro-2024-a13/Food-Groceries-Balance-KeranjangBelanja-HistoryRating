package heymart.backend.models;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Rating() {
    }

    public Rating(Long ownerId, Long marketId, int score, String review) {
        this.ownerId = ownerId;
        this.marketId = marketId;
        this.score = score;
        this.review = review;
    }

    public void setScore(int score) {
        this.score = score;
    }
}