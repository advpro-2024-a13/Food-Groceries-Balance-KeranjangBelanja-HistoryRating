package heymart.backend.service;

import heymart.backend.models.Product;
import org.springframework.stereotype.Service;

import heymart.backend.models.History;
import heymart.backend.repository.HistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistRepository histRepository;

    public HistoryServiceImpl(HistRepository histRepository) {
        this.histRepository = histRepository;
    }

    @Override
    public History getHistoryById(Long id) {
        Optional<History> history = histRepository.findById(id);
        return history.orElse(null);
    }

    @Override
    public History addNewHistory(Long ownerId, Long marketId, List<Product> purchases, double totalSpent) {
        return histRepository.save(new History(ownerId, marketId, purchases, totalSpent));
    }

    @Override
    public void deleteHistory(Long id) {
        histRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return histRepository.existsById(id);
    }

}