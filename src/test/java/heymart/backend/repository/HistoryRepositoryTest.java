package heymart.backend.repository;

import heymart.backend.models.History;
import heymart.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryRepositoryTest {
    HistRepository historyRepository;
    List<History> histories;

    @BeforeEach
    void setUp() {
        historyRepository = new HistRepository();

        histories = new ArrayList<>();

        List<Product> purchases1 = new ArrayList<>();
        purchases1.add(new Product(1L, "Product 1", 10.0));
        purchases1.add(new Product(2L, "Product 2", 15.0));
        History history1 = new History(1L, 1L, purchases1, 25.0);
        histories.add(history1);
        historyRepository.save(history1);

        List<Product> purchases2 = new ArrayList<>();
        purchases2.add(new Product(3L, "Product 3", 20.0));
        History history2 = new History(1L, 2L, purchases2, 20.0);
        histories.add(history2);
        historyRepository.save(history2);

        List<Product> purchases3 = new ArrayList<>();
        purchases3.add(new Product(4L, "Product 4", 30.0));
        History history3 = new History(2L, 1L, purchases3, 30.0);
        histories.add(history3);
        historyRepository.save(history3);

        List<Product> purchases4 = new ArrayList<>();
        purchases4.add(new Product(5L, "Product 5", 25.0));
        History history4 = new History(2L, 2L, purchases4, 25.0);
        histories.add(history4);
        historyRepository.save(history4);
    }

    @Test
    void testSaveCreate() {
        List<Product> purchases = new ArrayList<>();
        purchases.add(new Product(6L, "Product 6", 40.0));
        History newHistory = new History(null, 3L, purchases, 40.0);
        historyRepository.save(newHistory);
        List<History> allHistories = historyRepository.findAll();
        assertTrue(allHistories.contains(newHistory));
    }

    @Test
    void testSaveUpdate() {
        History existingHistory = histories.get(0);
        List<Product> newPurchases = new ArrayList<>();
        newPurchases.add(new Product(1L, "Product 1", 10.0)); // Same product with different price
        existingHistory.setPurchases(newPurchases);
        existingHistory.setTotalSpent(10.0); // Updated total spent
        historyRepository.save(existingHistory);
        History updatedHistory = historyRepository.findById(existingHistory.getId());
        assertEquals(10.0, updatedHistory.getTotalSpent());
        assertEquals(newPurchases, updatedHistory.getPurchases());
    }

    @Test
    void findByOwnerId() {
        List<History> historiesForOwner1 = historyRepository.findByOwnerId(1L);
        assertEquals(2, historiesForOwner1.size());
        assertTrue(historiesForOwner1.contains(histories.get(0)));
        assertTrue(historiesForOwner1.contains(histories.get(1)));
    }

    @Test
    void findByMarketId() {
        List<History> historiesForMarket2 = historyRepository.findByMarketId(2L);
        assertEquals(2, historiesForMarket2.size());
        assertTrue(historiesForMarket2.contains(histories.get(1)));
        assertTrue(historiesForMarket2.contains(histories.get(3)));
    }

    @Test
    void testDelete() {
        History historyToDelete = histories.get(0);
        historyRepository.delete(historyToDelete);
        List<History> allHistories = historyRepository.findAll();
        assertFalse(allHistories.contains(historyToDelete));
    }

    @Test
    void testFindAll() {
        List<History> allHistories = historyRepository.findAll();
        assertEquals(histories.size(), allHistories.size());
        assertTrue(allHistories.containsAll(histories));
    }
}
