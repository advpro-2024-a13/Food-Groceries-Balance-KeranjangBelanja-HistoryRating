package heymart.backend.service;

import heymart.backend.models.History;
import heymart.backend.models.Product;

import java.util.List;

public interface HistoryService {
    History getHistoryById(Long id);
    History addNewHistory(Long ownerId, Long marketId, List<Product> purchases, double totalSpent);
    void deleteHistory(Long id);
    boolean existsById(Long id);
}
