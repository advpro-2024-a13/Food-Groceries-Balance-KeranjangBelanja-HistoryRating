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
public class BalanceControllerTest {

    @Mock
    private BalanceServiceImpl balanceService;

    @InjectMocks
    private BalanceController balanceController;

    @Test
    public void testModifyBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "100");
        ResponseEntity<?> response = balanceController.modifyBalance(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 modified.", response.getBody());
    }

    @Test
    public void testGetBalanceById() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        when(balanceService.getBalanceById(any(Long.class))).thenReturn(new Balance());
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceController.getBalanceById(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddNewBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceController.addNewBalance(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 added.", response.getBody());
    }

    @Test
    public void testDeleteBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        ResponseEntity<?> response = balanceController.deleteBalance(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 deleted.", response.getBody());
    }

    @Test
    public void testTopUp() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        when(balanceService.getBalanceById(any(Long.class))).thenReturn(new Balance());
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "50");
        JSON.put("role", "ROLE_PEMBELI");
        ResponseEntity<?> response = balanceController.topUp(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 topped up.", response.getBody());
    }

    @Test
    public void testWithdraw() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        when(balanceService.getBalanceById(any(Long.class))).thenReturn(new Balance());
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "50");
        JSON.put("role", "ROLE_PENGELOLA");
        ResponseEntity<?> response = balanceController.withdraw(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 withdrawn.", response.getBody());
    }
}