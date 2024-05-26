package heymart.backend.controller;

import heymart.backend.models.History;
import heymart.backend.models.Product;
import heymart.backend.service.HistoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class HistoryControllerTest {

    @InjectMocks
    private HistoryController historyController;

    @Mock
    private HistoryServiceImpl historyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getHistoryById_ExistingId_ReturnsHistory() {
        Long id = 1L;
        History history = new History.Builder().build();
        when(historyService.existsById(id)).thenReturn(true);
        when(historyService.getHistoryById(id)).thenReturn(history);

        ResponseEntity<?> response = historyController.getHistoryById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(history, response.getBody());
    }

    @Test
    void getHistoryById_NonExistingId_ReturnsBadRequest() {
        Long id = 1L;
        when(historyService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = historyController.getHistoryById(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("History with id " + id + " not found.", response.getBody());
    }

    @Test
    void addNewHistory_ValidRequest_ReturnsNewHistoryId() {
        Long ownerId = 1L;
        Long marketId = 2L;
        List<Product> purchases = new ArrayList<>();
        double totalSpent = 100.0;
        History newHistory = new History.Builder().build();
        when(historyService.addNewHistory(ownerId, marketId, purchases, totalSpent)).thenReturn(newHistory);

        HashMap<String, Object> request = new HashMap<>();
        request.put("ownerId", ownerId.toString());
        request.put("marketId", marketId.toString());
        request.put("purchases", purchases);
        request.put("totalSpent", totalSpent);

        ResponseEntity<?> response = historyController.addNewHistory(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New history added with id: " + newHistory.getId(), response.getBody());
    }

    @Test
    void deleteHistory_ExistingId_ReturnsOkMessage() {
        Long id = 1L;
        when(historyService.existsById(id)).thenReturn(true);

        ResponseEntity<?> response = historyController.deleteHistory(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("History with id " + id + " deleted.", response.getBody());
        verify(historyService, times(1)).deleteHistory(id);
    }

    @Test
    void deleteHistory_NonExistingId_ReturnsBadRequest() {
        Long id = 1L;
        when(historyService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = historyController.deleteHistory(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("History with id " + id + " not found.", response.getBody());
        verify(historyService, never()).deleteHistory(anyLong());
    }

    @Test
    void getAllHistory_ReturnsListOfHistories() {
        List<History> histories = new ArrayList<>();
        when(historyService.getAllHistory()).thenReturn(CompletableFuture.completedFuture(histories));

        CompletableFuture<ResponseEntity<List<History>>> responseFuture = historyController.getAllHistory();
        ResponseEntity<List<History>> response = responseFuture.join();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(histories, response.getBody());
    }

    @Test
    void getHistoryByOwnerId_ReturnsListOfHistories() {
        Long ownerId = 1L;
        List<History> histories = new ArrayList<>();
        when(historyService.getHistoryByOwnerId(ownerId)).thenReturn(CompletableFuture.completedFuture(histories));

        CompletableFuture<ResponseEntity<List<History>>> responseFuture = historyController.getHistoryByOwnerId(ownerId);
        ResponseEntity<List<History>> response = responseFuture.join();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(histories, response.getBody());
    }

    @Test
    void getHistoryByMarketId_ReturnsListOfHistories() {
        Long marketId = 1L;
        List<History> histories = new ArrayList<>();
        when(historyService.getHistoryByMarketId(marketId)).thenReturn(CompletableFuture.completedFuture(histories));

        CompletableFuture<ResponseEntity<List<History>>> responseFuture = historyController.getHistoryByMarketId(marketId);
        ResponseEntity<List<History>> response = responseFuture.join();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(histories, response.getBody());
    }

    @Test
    void handleException_ReturnsInternalServerErrorResponse() {
        Throwable throwable = new RuntimeException("Internal server error");
        ResponseEntity<List<History>> response = historyController.handleException(throwable);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}