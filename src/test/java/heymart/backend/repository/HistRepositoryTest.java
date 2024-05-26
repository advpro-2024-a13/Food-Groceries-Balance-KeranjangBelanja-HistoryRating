package heymart.backend.repository;

import heymart.backend.models.History;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HistRepositoryTest {

    @Mock
    private HistRepository historyRepositoryMock;

    // Happy Path

    @Test
    void findByOwnerId_ReturnsHistoriesForOwnerId() {
        Long ownerId = 1L;
        List<History> expectedHistories = new ArrayList<>();
        when(historyRepositoryMock.findByOwnerId(ownerId)).thenReturn(Optional.of(expectedHistories));

        Optional<List<History>> actualHistories = historyRepositoryMock.findByOwnerId(ownerId);

        assertEquals(expectedHistories, actualHistories.orElse(null));
        verify(historyRepositoryMock, times(1)).findByOwnerId(ownerId);
    }

    @Test
    void findByMarketId_ReturnsHistoriesForMarketId() {
        Long marketId = 1L;
        List<History> expectedHistories = new ArrayList<>();
        when(historyRepositoryMock.findByMarketId(marketId)).thenReturn(Optional.of(expectedHistories));

        Optional<List<History>> actualHistories = historyRepositoryMock.findByMarketId(marketId);

        assertEquals(expectedHistories, actualHistories.orElse(null));
        verify(historyRepositoryMock, times(1)).findByMarketId(marketId);
    }

    @Test
    void findById_ReturnsHistoryForId() {
        Long id = 1L;
        History expectedHistory = new History.Builder().build();
        when(historyRepositoryMock.findById(id)).thenReturn(Optional.of(expectedHistory));

        Optional<History> actualHistory = historyRepositoryMock.findById(id);

        assertEquals(expectedHistory, actualHistory.orElse(null));
        verify(historyRepositoryMock, times(1)).findById(id);
    }

    @Test
    void save_SavesHistory() {
        History history = new History.Builder().build();
        when(historyRepositoryMock.save(history)).thenReturn(history);

        History savedHistory = historyRepositoryMock.save(history);

        assertEquals(history, savedHistory);
        verify(historyRepositoryMock, times(1)).save(history);
    }

    @Test
    void existsById_ReturnsTrue() {
        Long id = 1L;
        when(historyRepositoryMock.existsById(id)).thenReturn(true);

        boolean exists = historyRepositoryMock.existsById(id);

        assertTrue(exists);
        verify(historyRepositoryMock, times(1)).existsById(id);
    }

    @Test
    void findAll_ReturnsAllHistories() {
        List<History> expectedHistories = new ArrayList<>();
        when(historyRepositoryMock.findAll()).thenReturn(expectedHistories);

        List<History> actualHistories = historyRepositoryMock.findAll();

        assertEquals(expectedHistories, actualHistories);
        verify(historyRepositoryMock, times(1)).findAll();
    }

    // Unhappy Path

    @Test
    void findByOwnerId_ReturnsEmptyOptionalForNonExistingOwnerId() {
        Long ownerId = 1L;
        when(historyRepositoryMock.findByOwnerId(ownerId)).thenReturn(Optional.empty());

        Optional<List<History>> actualHistories = historyRepositoryMock.findByOwnerId(ownerId);

        assertFalse(actualHistories.isPresent());
        verify(historyRepositoryMock, times(1)).findByOwnerId(ownerId);
    }

    @Test
    void findByMarketId_ReturnsEmptyOptionalForNonExistingMarketId() {
        Long marketId = 1L;
        when(historyRepositoryMock.findByMarketId(marketId)).thenReturn(Optional.empty());

        Optional<List<History>> actualHistories = historyRepositoryMock.findByMarketId(marketId);

        assertFalse(actualHistories.isPresent());
        verify(historyRepositoryMock, times(1)).findByMarketId(marketId);
    }

    @Test
    void findById_ReturnsEmptyOptionalForNonExistingId() {
        Long id = 1L;
        when(historyRepositoryMock.findById(id)).thenReturn(Optional.empty());

        Optional<History> actualHistory = historyRepositoryMock.findById(id);

        assertFalse(actualHistory.isPresent());
        verify(historyRepositoryMock, times(1)).findById(id);
    }

    @Test
    void existsById_ReturnsFalseForNonExistingId() {
        Long id = 1L;
        when(historyRepositoryMock.existsById(id)).thenReturn(false);

        boolean exists = historyRepositoryMock.existsById(id);

        assertFalse(exists);
        verify(historyRepositoryMock, times(1)).existsById(id);
    }

    @Test
    void deleteById_DoesNotThrowExceptionForNonExistingId() {
        Long id = 1L;
        doNothing().when(historyRepositoryMock).deleteById(id);

        historyRepositoryMock.deleteById(id);

        verify(historyRepositoryMock, times(1)).deleteById(id);
    }
}