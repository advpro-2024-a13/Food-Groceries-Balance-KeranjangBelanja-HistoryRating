package heymart.backend.service;

import heymart.backend.models.Product;
import heymart.backend.models.History;
import heymart.backend.repository.HistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistRepository histRepository;

    @Override
    public History getHistoryById(Long id) {
        return histRepository.findById(id).orElse(null);
    }

    @Override
    public History addNewHistory(Long ownerId, Long marketId, List<Product> purchases, double totalSpent) {
        History history = new History.Builder()
                .ownerId(ownerId)
                .marketId(marketId)
                .purchases(purchases)
                .totalSpent(totalSpent)
                .build();
        return histRepository.save(history);
    }

    @Override
    public void deleteHistory(Long id) {
        histRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return histRepository.existsById(id);
    }

    @Override
    public CompletableFuture<List<History>> getAllHistory() {
        List<History> allHistory = histRepository.findAll();
        allHistory.forEach(history -> history.getPurchases().size());
        return CompletableFuture.completedFuture(allHistory);
    }

    @Override
    public CompletableFuture<List<History>> getHistoryByOwnerId(Long ownerId) {
        List<History> historyByOwnerId = histRepository.findByOwnerId(ownerId).orElse(List.of());
        historyByOwnerId.forEach(history -> history.getPurchases().size());
        return CompletableFuture.completedFuture(historyByOwnerId);
    }

    @Override
    public CompletableFuture<List<History>> getHistoryByMarketId(Long marketId) {
        List<History> historyByMarketId = histRepository.findByMarketId(marketId).orElse(List.of());
        historyByMarketId.forEach(history -> history.getPurchases().size());
        return CompletableFuture.completedFuture(historyByMarketId);
    }
}
