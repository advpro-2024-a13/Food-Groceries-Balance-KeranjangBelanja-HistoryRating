package heymart.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import heymart.backend.models.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HistoryRepositoryTest {

    @Mock
    private HistRepository histRepository;
    private List<History> histories;

    @BeforeEach
    public void setUp() {
        histories = new ArrayList<>();
        histories.add(new History(1L, 1L, new ArrayList<>(), 100.0));
        histories.add(new History(1L, 2L, new ArrayList<>(), 200.0));
        histories.add(new History(2L, 1L, new ArrayList<>(), 300.0));
    }

    @Test
    public void testFindByOwnerId() {
        Long ownerId = 1L;
        List<History> expectedHistories = new ArrayList<>();
        expectedHistories.add(histories.get(0));
        expectedHistories.add(histories.get(1));

        when(histRepository.findByOwnerId(ownerId)).thenReturn(Optional.of(expectedHistories));

        Optional<List<History>> foundHistories = histRepository.findByOwnerId(ownerId);

        assertEquals(expectedHistories, foundHistories.get());
    }

    @Test
    public void testFindByMarketId() {
        Long marketId = 1L;
        List<History> expectedHistories = new ArrayList<>();
        expectedHistories.add(histories.get(0));
        expectedHistories.add(histories.get(2));

        when(histRepository.findByMarketId(marketId)).thenReturn(Optional.of(expectedHistories));

        Optional<List<History>> foundHistories = histRepository.findByMarketId(marketId);

        assertEquals(expectedHistories, foundHistories.get());
    }
}