package heymart.backend.repository;

import heymart.backend.models.History;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistRepository {
    private List<History> histories = new ArrayList<>();

    public void save(History history) {
        if (history.getId() == null) {
            history.setId((long) (histories.size() + 1));
            histories.add(history);
        } else {
            // Update an existing history
            int index = histories.indexOf(history);
            if (index != -1) {
                histories.set(index, history);
            }
        }
    }

    public History findById(Long id) {
        return histories.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<History> findByOwnerId(Long ownerId) {
        return histories.stream()
                .filter(h -> h.getOwnerId().equals(ownerId))
                .collect(Collectors.toList());
    }

    public List<History> findByMarketId(Long marketId) {
        return histories.stream()
                .filter(h -> h.getMarketId().equals(marketId))
                .collect(Collectors.toList());
    }

    public void delete(History history) {
        histories.remove(history);
    }

    public List<History> findAll() {
        return new ArrayList<>(histories);
    }
}
