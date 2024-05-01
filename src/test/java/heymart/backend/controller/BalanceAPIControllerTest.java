package heymart.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import heymart.backend.models.Balance;
import heymart.backend.service.BalanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class BalanceAPIControllerTest {

    @Mock
    private BalanceServiceImpl balanceService;

    @InjectMocks
    private BalanceAPIController balanceAPIController;

    @Test
    public void testGetBalanceById() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        when(balanceService.getBalanceById(any(Long.class))).thenReturn(new Balance());
        ResponseEntity<?> response = balanceAPIController.getBalanceById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFailedGetBalanceById() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        ResponseEntity<?> response = balanceAPIController.getBalanceById(1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance with ownerId 1 not found.", response.getBody());
    }

    @Test
    public void testAddNewBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceAPIController.addNewBalance(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 added.", response.getBody());
    }

    @Test
    public void testFailedAddNewBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceAPIController.addNewBalance(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance with ownerId 1 already exists.", response.getBody());
    }

    @Test
    public void testDeleteBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceAPIController.deleteBalance(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 deleted.", response.getBody());
    }

    @Test
    public void testFailedDeleteBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceAPIController.deleteBalance(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance with ownerId 1 not found.", response.getBody());
    }
}