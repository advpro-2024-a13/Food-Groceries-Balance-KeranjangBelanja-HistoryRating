package heymart.backend.repository;

import models.History;
import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryRepositoryTest {
    HistoryRepository historyRepository;

    List<History> histories;

    @BeforeEach
    void setUp() {
        historyRepository = new HistoryRepository();

        histories = new ArrayList<>();

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(1000);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(2000);

        History history1 = new History(1L, 1L, List.of(product1, product2), 3000);
        histories.add(history1);
        History history2 = new History(2L, 2L, List.of(product1, product2), 3000);
        histories.add(history2);
        History history3 = new History(3L, 3L, List.of(product1, product2), 3000);
        histories.add(history3);
        History history4 = new History(4L, 4L, List.of(product1, product2), 3000);
        histories.add(history4);
    }

    @Test
    void testSaveCreate() {
        History newHistory = new History(5L, 5L, List.of(new Product(), new Product()), 6000);
        historyRepository.save(newHistory);
        List<History> allHistories = historyRepository.findAll();
        assertTrue(allHistories.contains(newHistory));
    }

    @Test
    void testSaveUpdate() {
        History existingHistory = histories.get(0);
        existingHistory.setTotal(4000);
        historyRepository.save(existingHistory);
        History updatedHistory = historyRepository.findById(existingHistory.getId());
        assertEquals(4000, updatedHistory.getTotal());
    }

    @Test
    void findByOwnerId() {
        List<History> historiesForOwner1 = historyRepository.findByOwnerId(1L);
        assertEquals(1, historiesForOwner1.size());
        assertTrue(historiesForOwner1.contains(histories.get(0)));
    }

    @Test
    void findByMarketId() {
        List<History> historiesForMarket1 = historyRepository.findByMarketId(1L);
        assertEquals(1, historiesForMarket1.size());
        assertTrue(historiesForMarket1.contains(histories.get(0)));
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