package heymart.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import heymart.backend.enums.EnumRoleSingleton;
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
    public void testFailedModifyBalance() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "100");
        ResponseEntity<?> response = balanceController.modifyBalance(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance with ownerId 1 not found.", response.getBody());
    }

    @Test
    public void testTopUp() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        when(balanceService.getBalanceById(any(Long.class))).thenReturn(new Balance());
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "50");
        String roleEnum = EnumRoleSingleton.INSTANCE.getStringValue("Pembeli");
        JSON.put("role", roleEnum);
        ResponseEntity<?> response = balanceController.topUp(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 topped up.", response.getBody());
    }

    @Test
    public void testTopUpIfBalanceNotExist() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "50");
        String roleEnum = EnumRoleSingleton.INSTANCE.getStringValue("Pembeli");
        JSON.put("role", roleEnum);
        ResponseEntity<?> response = balanceController.topUp(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Param Invalid", response.getBody());
    }

    @Test
    public void testTopUpParamInvalid() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "50");
        String roleEnum = EnumRoleSingleton.INSTANCE.getStringValue("Pengelola");
        JSON.put("role", roleEnum);
        ResponseEntity<?> response = balanceController.topUp(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Param Invalid", response.getBody());
    }

    @Test
    public void testWithdraw() {
        when(balanceService.existsById(any(Long.class))).thenReturn(true);
        when(balanceService.getBalanceById(any(Long.class))).thenReturn(new Balance());
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "50");
        String roleEnum = EnumRoleSingleton.INSTANCE.getStringValue("Pengelola");
        JSON.put("role", roleEnum);

        balanceService.addNewBalance(1L);
        balanceService.modifyBalance(1L, 100L);

        ResponseEntity<?> response = balanceController.withdraw(JSON);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Balance with ownerId 1 withdrawn.", response.getBody());
    }

    @Test
    public void testWithdrawIfBalanceNotExist() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "50");
        String roleEnum = EnumRoleSingleton.INSTANCE.getStringValue("Pengelola");
        JSON.put("role", roleEnum);
        ResponseEntity<?> response = balanceController.withdraw(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Param Invalid", response.getBody());
    }

    @Test
    public void testWithdrawParamInvalid() {
        when(balanceService.existsById(any(Long.class))).thenReturn(false);
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("ownerId", "1");
        JSON.put("amount", "50");
        String roleEnum = EnumRoleSingleton.INSTANCE.getStringValue("Pembeli");
        JSON.put("role", roleEnum);
        ResponseEntity<?> response = balanceController.withdraw(JSON);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Param Invalid", response.getBody());
    }
}