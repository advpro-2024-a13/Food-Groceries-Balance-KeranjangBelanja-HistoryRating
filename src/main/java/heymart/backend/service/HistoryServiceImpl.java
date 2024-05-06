package heymart.backend.service;

import heymart.backend.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heymart.backend.models.History;
import heymart.backend.repository.HistRepository;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistRepository histRepository;

    @Override
    public History getHistoryById(Long id) {
        return histRepository.findById(id).get();
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