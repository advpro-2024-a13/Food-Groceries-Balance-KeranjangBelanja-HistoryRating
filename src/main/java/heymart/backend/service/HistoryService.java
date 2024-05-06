package heymart.backend.service;

import heymart.backend.models.History;
import heymart.backend.models.Product;

import java.util.List;

public interface HistoryService {
    public History getHistoryById(Long id);
    public History addNewHistory(Long ownerId, Long marketId, List<Product> purchases, double totalSpent);
    public void deleteHistory(Long id);
    public boolean existsById(Long id);
}
