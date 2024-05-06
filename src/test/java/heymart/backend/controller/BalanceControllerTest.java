package heymart.backend.controller;

import heymart.backend.enums.EnumRoleSingleton;
import heymart.backend.service.BalanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        JSON.put("amount", "1000");
        ResponseEntity<?> response = balanceController.modifyBalance(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 modified.", response.getBody());
    }

    @Test
    public void testModifyBalanceInvalidAmount() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "-500");
        ResponseEntity<?> response = balanceController.modifyBalance(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Amount harus lebih dari 0", response.getBody());
    }

    @Test
    public void testModifyBalanceBalanceNotFound() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "2");
        JSON.put("amount", "1000");
        ResponseEntity<?> response = balanceController.modifyBalance(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance not found", response.getBody());
    }

    @Test
    public void testTopUp() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        when(balanceService.getBalanceById(any(Long.class))).thenReturn(1000L);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "500");
        JSON.put("role", EnumRoleSingleton.INSTANCE.getStringValue("Pembeli"));
        ResponseEntity<?> response = balanceController.topUp(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 topped up.", response.getBody());
    }

    @Test
    public void testTopUpInvalidAmount() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "-500");
        JSON.put("role", EnumRoleSingleton.INSTANCE.getStringValue("Pembeli"));

        when(balanceService.existsById(1L)).thenReturn(true);

        ResponseEntity<?> response = balanceController.topUp(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Amount harus lebih dari 0", response.getBody());
    }

    @Test
    public void testTopUpInvalidRole() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "500");
        JSON.put("role", EnumRoleSingleton.INSTANCE.getStringValue("Pengelola"));

        when(balanceService.existsById(1L)).thenReturn(true);

        ResponseEntity<?> response = balanceController.topUp(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Role not valid", response.getBody());
    }

    @Test
    public void testTopUpBalanceNotFound() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "2");
        JSON.put("amount", "500");
        JSON.put("role", EnumRoleSingleton.INSTANCE.getStringValue("Pembeli"));
        ResponseEntity<?> response = balanceController.topUp(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance not found", response.getBody());
    }

    @Test
    public void testWithdraw() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        when(balanceService.getBalanceById(any(Long.class))).thenReturn(1500L);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "500");
        JSON.put("role", EnumRoleSingleton.INSTANCE.getStringValue("Pengelola"));
        ResponseEntity<?> response = balanceController.withdraw(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 withdrawn.", response.getBody());
    }

    @Test
    public void testWithdrawBalanceNotEnough() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "2000");
        JSON.put("role", EnumRoleSingleton.INSTANCE.getStringValue("Pengelola"));

        when(balanceService.existsById(1L)).thenReturn(true);
        when(balanceService.getBalanceById(1L)).thenReturn(500L);

        ResponseEntity<?> response = balanceController.withdraw(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance not enough", response.getBody());
    }

    @Test
    public void testWithdrawInvalidAmount() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "-500");
        JSON.put("role", EnumRoleSingleton.INSTANCE.getStringValue("Pengelola"));

        when(balanceService.existsById(1L)).thenReturn(true);

        ResponseEntity<?> response = balanceController.withdraw(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Amount harus lebih dari 0", response.getBody());
    }

    @Test
    public void testWithdrawBalanceNotFound() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "100");
        JSON.put("amount", "500");
        JSON.put("role", EnumRoleSingleton.INSTANCE.getStringValue("Pengelola"));

        when(balanceService.existsById(100L)).thenReturn(false);

        ResponseEntity<?> response = balanceController.withdraw(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance not found", response.getBody());
    }

    @Test
    public void testWithdrawInvalidRole() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "1");
        JSON.put("role", EnumRoleSingleton.INSTANCE.getStringValue("Pembeli"));

        when(balanceService.existsById(1L)).thenReturn(true);
        when(balanceService.getBalanceById(1L)).thenReturn(500L);

        ResponseEntity<?> response = balanceController.withdraw(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Role not valid", response.getBody());
    }
}