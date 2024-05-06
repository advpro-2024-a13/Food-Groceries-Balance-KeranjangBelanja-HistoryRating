package heymart.backend.service;

import heymart.backend.models.History;
import heymart.backend.models.Product;
import heymart.backend.repository.HistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceImplTest {

    @InjectMocks
    private HistoryServiceImpl historyService;

    @Mock
    private HistRepository histRepository;

    private List<Product> purchases;
    private History history;

    @BeforeEach
    void setUp() {
        // Set up test data
        purchases = new ArrayList<>();
        purchases.add(new Product(1L, "Product 1", 100));
        purchases.add(new Product(2L, "Product 2", 150));
        history = new History(1L, 1L, purchases, 250.0);
    }

    @Test
    void testGetHistoryById() {
        // Mocking repository behavior
        when(histRepository.findById(1L)).thenReturn(Optional.of(history));

        // Calling the method under test
        History result = historyService.getHistoryById(1L);

        // Verifying the result
        assertEquals(history, result);
    }

    @Test
    void testAddNewHistory() {
        // Mocking repository behavior
        when(histRepository.save(any())).thenReturn(history);

        // Calling the method under test
        History result = historyService.addNewHistory(1L, 1L, purchases, 250.0);

        // Verifying the result
        assertEquals(history, result);
    }

    @Test
    void testDeleteHistory() {
        // Calling the method under test
        historyService.deleteHistory(1L);

        // Verifying that the deleteById method of the repository was called once with the correct argument
        verify(histRepository, times(1)).deleteById(1L);
    }

    @Test
    void testExistsById() {
        // Mocking repository behavior
        when(histRepository.existsById(1L)).thenReturn(true);

        // Calling the method under test
        boolean result = historyService.existsById(1L);

        // Verifying the result
        assertTrue(result);
    }
}
