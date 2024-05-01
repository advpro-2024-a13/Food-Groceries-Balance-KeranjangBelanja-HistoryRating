package heymart.backend.repository;

import heymart.backend.models.Rating;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RatingRepository {
    private List<Rating> ratings = new ArrayList<>();

    public void save(Rating rating) {
        if (rating.getId() == null) {
            rating.setId((long) (ratings.size() + 1));
            ratings.add(rating);
        } else {
            int index = ratings.indexOf(rating);
            if (index != -1) {
                ratings.set(index, rating);
            }
        }
    }

    public Rating findById(Long id) {
        return ratings.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Rating> findByOwnerId(Long ownerId) {
        return ratings.stream()
                .filter(r -> r.getOwnerId().equals(ownerId))
                .collect(Collectors.toList());
    }

    public List<Rating> findByMarketId(Long marketId) {
        return ratings.stream()
                .filter(r -> r.getMarketId().equals(marketId))
                .collect(Collectors.toList());
    }

    public void delete(Rating rating) {
        ratings.remove(rating);
    }

    public List<Rating> findAll() {
        return new ArrayList<>(ratings);
    }
}