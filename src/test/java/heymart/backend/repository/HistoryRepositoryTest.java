package heymart.backend.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import heymart.backend.models.History;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HistoryRepositoryTest {

    @Mock
    private HistRepository histRepository;

    @Test
    public void testFindByOwnerId() {
        History history = new History();
        history.setOwnerId(123L);
        history.setMarketId(456L);

        when(histRepository.findByOwnerId(123L)).thenReturn(Optional.of(List.of(history)));

        Optional<List<History>> foundHistory = histRepository.findByOwnerId(123L);

        assertTrue(foundHistory.isPresent());
        assertEquals(123L, foundHistory.get().getFirst().getOwnerId());
        assertEquals(456L, foundHistory.get().getFirst().getMarketId());
    }

    @Test
    public void testFindByMarketId() {
        History history = new History();
        history.setOwnerId(123L);
        history.setMarketId(456L);

        when(histRepository.findByMarketId(456L)).thenReturn(Optional.of(List.of(history)));

        Optional<List<History>> foundHistory = histRepository.findByMarketId(456L);

        assertTrue(foundHistory.isPresent());
        assertEquals(123L, foundHistory.get().getFirst().getOwnerId());
        assertEquals(456L, foundHistory.get().getFirst().getMarketId());
    }
}