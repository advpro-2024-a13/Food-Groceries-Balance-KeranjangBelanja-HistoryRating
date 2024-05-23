package heymart.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import heymart.backend.models.History;
import heymart.backend.models.Product;
import heymart.backend.service.HistoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HistoryControllerTest {

    @Mock
    private HistoryServiceImpl historyService;

    @InjectMocks
    private HistoryController historyController;

    @Test
    public void testGetHistoryById() {
        Long id = 1L;
        History history = new History();
        when(historyService.existsById(id)).thenReturn(true);
        when(historyService.getHistoryById(id)).thenReturn(history);

        ResponseEntity<?> response = historyController.getHistoryById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(history, response.getBody());
    }

    @Test
    public void testGetHistoryByIdNotFound() {
        Long id = 1L;
        when(historyService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = historyController.getHistoryById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("History with id " + id + " not found.", response.getBody());
    }

    @Test
    public void testAddNewHistory() {
        Long ownerId = 1L;
        Long marketId = 2L;
        List<Product> purchases = new ArrayList<>();
        double totalSpent = 100.0;
        History newHistory = new History(ownerId, marketId, purchases, totalSpent);
        when(historyService.addNewHistory(ownerId, marketId, purchases, totalSpent)).thenReturn(newHistory);

        HashMap<String, Object> request = new HashMap<>();
        request.put("ownerId", ownerId);
        request.put("marketId", marketId);
        request.put("purchases", purchases);
        request.put("totalSpent", totalSpent);

        ResponseEntity<?> response = historyController.addNewHistory(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New history added with id: " + newHistory.getId(), response.getBody());
    }

    @Test
    public void testDeleteHistory() {
        Long id = 1L;
        when(historyService.existsById(id)).thenReturn(true);

        ResponseEntity<?> response = historyController.deleteHistory(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("History with id " + id + " deleted.", response.getBody());
    }

    @Test
    public void testDeleteHistoryNotFound() {
        Long id = 1L;
        when(historyService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = historyController.deleteHistory(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("History with id " + id + " not found.", response.getBody());
    }
}