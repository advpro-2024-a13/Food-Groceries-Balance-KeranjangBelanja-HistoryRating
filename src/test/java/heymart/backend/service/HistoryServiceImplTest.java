package heymart.backend.service;

import heymart.backend.models.History;
import heymart.backend.models.Product;
import heymart.backend.repository.HistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HistoryServiceImplTest {

    @InjectMocks
    private HistoryServiceImpl historyService;

    @Mock
    private HistRepository histRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getHistoryById_ExistingId_ReturnsHistory() {
        Long id = 1L;
        History expectedHistory = new History.Builder().build();
        when(histRepository.findById(id)).thenReturn(Optional.of(expectedHistory));

        History actualHistory = historyService.getHistoryById(id);

        assertEquals(expectedHistory, actualHistory);
    }

    @Test
    void getHistoryById_NonExistingId_ReturnsNull() {
        Long id = 1L;
        when(histRepository.findById(id)).thenReturn(Optional.empty());

        History actualHistory = historyService.getHistoryById(id);

        assertNull(actualHistory);
    }

    @Test
    void addNewHistory_ValidInput_ReturnsNewHistory() {
        Long ownerId = 1L;
        Long marketId = 2L;
        List<Product> purchases = new ArrayList<>();
        double totalSpent = 100.0;
        History expectedHistory = new History.Builder()
                .ownerId(ownerId)
                .marketId(marketId)
                .purchases(purchases)
                .totalSpent(totalSpent)
                .build();
        when(histRepository.save(any(History.class))).thenReturn(expectedHistory);

        History actualHistory = historyService.addNewHistory(ownerId, marketId, purchases, totalSpent);

        assertEquals(expectedHistory, actualHistory);
        verify(histRepository, times(1)).save(any(History.class));
    }

    @Test
    void deleteHistory_ExistingId_DeletesHistory() {
        Long id = 1L;
        when(histRepository.existsById(id)).thenReturn(true);

        historyService.deleteHistory(id);

        verify(histRepository, times(1)).deleteById(id);
    }

    @Test
    void existsById_ExistingId_ReturnsTrue() {
        Long id = 1L;
        when(histRepository.existsById(id)).thenReturn(true);

        boolean exists = historyService.existsById(id);

        assertTrue(exists);
    }

    @Test
    void existsById_NonExistingId_ReturnsFalse() {
        Long id = 1L;
        when(histRepository.existsById(id)).thenReturn(false);

        boolean exists = historyService.existsById(id);

        assertFalse(exists);
    }

    @Test
    void getAllHistory_ReturnsAllHistories() {
        List<History> expectedHistories = new ArrayList<>();
        when(histRepository.findAll()).thenReturn(expectedHistories);

        CompletableFuture<List<History>> futureHistories = historyService.getAllHistory();
        List<History> actualHistories = futureHistories.join();

        assertEquals(expectedHistories, actualHistories);
    }

    @Test
    void getHistoryByOwnerId_ReturnsHistoriesForOwnerId() {
        Long ownerId = 1L;
        List<History> expectedHistories = new ArrayList<>();
        when(histRepository.findByOwnerId(ownerId)).thenReturn(Optional.of(expectedHistories));

        CompletableFuture<List<History>> futureHistories = historyService.getHistoryByOwnerId(ownerId);
        List<History> actualHistories = futureHistories.join();

        assertEquals(expectedHistories, actualHistories);
    }

    @Test
    void getHistoryByMarketId_ReturnsHistoriesForMarketId() {
        Long marketId = 1L;
        List<History> expectedHistories = new ArrayList<>();
        when(histRepository.findByMarketId(marketId)).thenReturn(Optional.of(expectedHistories));

        CompletableFuture<List<History>> futureHistories = historyService.getHistoryByMarketId(marketId);
        List<History> actualHistories = futureHistories.join();

        assertEquals(expectedHistories, actualHistories);
    }
}