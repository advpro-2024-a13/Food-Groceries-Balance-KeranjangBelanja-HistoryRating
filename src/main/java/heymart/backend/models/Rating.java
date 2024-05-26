package heymart.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private Long marketId;
    private int score;
    private String review;

    public Rating() {
    }

    public Rating(Long ownerId, Long marketId, int score, String review) {
        this.ownerId = ownerId;
        this.marketId = marketId;
        setScore(score);
        this.review = review;
    }

    public void setScore(int score) {
        if (score < 1 || score > 5) {
            throw new IllegalArgumentException("Score must be between 1 and 5");
        }
        this.score = score;
    }
}
