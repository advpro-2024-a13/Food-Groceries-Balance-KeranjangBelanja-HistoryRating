package heymart.backend.controller;

import heymart.backend.models.Balance;
import heymart.backend.service.BalanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceAPIControllerTest {

    @Mock
    private BalanceServiceImpl balanceService;

    @InjectMocks
    private BalanceAPIController balanceAPIController;

    @Test
    void testGetBalanceById() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        when(balanceService.getBalanceById(any(Long.class))).thenReturn(0L);
        ResponseEntity<?> response = balanceAPIController.getBalanceById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0L, response.getBody());
    }

    @Test
    void testFailedGetBalanceById() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        ResponseEntity<?> response = balanceAPIController.getBalanceById(1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance with ownerId 1 not found.", response.getBody());
    }

    @Test
    void testAddNewBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceAPIController.addNewBalance(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 added.", response.getBody());
    }

    @Test
    void testFailedAddNewBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceAPIController.addNewBalance(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance with ownerId 1 already exists.", response.getBody());
    }

    @Test
    void testDeleteBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceAPIController.deleteBalance(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 deleted.", response.getBody());
    }

    @Test
    void testFailedDeleteBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceAPIController.deleteBalance(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance with ownerId 1 not found.", response.getBody());
    }

    @Test
    void testGetAllBalance() throws ExecutionException, InterruptedException {
        Iterable<Balance> balances = Arrays.asList(new Balance(), new Balance());
        when(balanceService.getAllBalance()).thenReturn(CompletableFuture.completedFuture(balances));

        CompletableFuture<ResponseEntity<Iterable<Balance>>> response = balanceAPIController.getAllBalance();

        assertEquals(HttpStatus.OK, response.get().getStatusCode());
        assertEquals(balances, response.get().getBody());
        verify(balanceService, times(1)).getAllBalance();
    }
}