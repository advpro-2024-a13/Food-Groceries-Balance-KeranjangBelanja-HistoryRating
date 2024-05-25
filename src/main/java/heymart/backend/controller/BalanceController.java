package heymart.backend.controller;

import heymart.backend.enums.EnumRoleSingleton;
import heymart.backend.service.BalanceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/balance")
public class BalanceController {
    
    private final BalanceServiceImpl balanceService;

    public BalanceController(BalanceServiceImpl balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping("/modifyBalance")
    public ResponseEntity<?> modifyBalance(@RequestBody Map<String, String> JSON) {
        long ownerId = Long.parseLong(JSON.get("ownerId"));
        long amount = Long.parseLong(JSON.get("amount"));
        if (amount <= 0) {
            return ResponseEntity
                    .badRequest()
                    .body("Amount harus lebih dari 0");
        } else if (!balanceService.existsById(ownerId)) {
            return ResponseEntity
                    .badRequest()
                    .body("Balance not found");
        } else {
            balanceService.modifyBalance(ownerId, amount);
            return ResponseEntity
                    .ok()
                    .body("Balance with ownerId " + ownerId + " modified.");
        }
    }

    @PostMapping("/topUp")
    public ResponseEntity<?> topUp(@RequestBody Map<String, String> JSON) {
        long ownerId = Long.parseLong(JSON.get("ownerId"));
        long amount = Long.parseLong(JSON.get("amount"));
        String role = JSON.get("role");
        String roleEnum = EnumRoleSingleton.INSTANCE.getStringValue("Pembeli");

        if (!balanceService.existsById(ownerId)) {
            return ResponseEntity
                    .badRequest()
                    .body("Balance not found");
        } else if (amount <= 0) {
            return ResponseEntity
                    .badRequest()
                    .body("Amount harus lebih dari 0");
        } else if (!role.equals(roleEnum)) {
            return ResponseEntity
                    .badRequest()
                    .body("Role not valid");
        } else {
            Long balance = balanceService.getBalanceById(ownerId);
            balanceService.modifyBalance(ownerId, balance + amount);
            return ResponseEntity
                    .ok()
                    .body("Balance with ownerId " + ownerId + " topped up.");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Map<String, String> JSON) {
        long ownerId = Long.parseLong(JSON.get("ownerId"));
        long amount = Long.parseLong(JSON.get("amount"));
        String role = JSON.get("role");
        String roleEnum = EnumRoleSingleton.INSTANCE.getStringValue("Pengelola");

        if (!balanceService.existsById(ownerId)) {
            return ResponseEntity
                    .badRequest()
                    .body("Balance not found");
        } else if (amount <= 0) {
            return ResponseEntity
                    .badRequest()
                    .body("Amount harus lebih dari 0");
        } else if (amount > balanceService.getBalanceById(ownerId)) {
            return ResponseEntity
                    .badRequest()
                    .body("Balance not enough");
        } else if (!role.equals(roleEnum)) {
            return ResponseEntity
                    .badRequest()
                    .body("Role not valid");
        } else {
            Long balance = balanceService.getBalanceById(ownerId);
            balanceService.modifyBalance(ownerId, balance - amount);
            return ResponseEntity
                    .ok()
                    .body("Balance with ownerId " + ownerId + " withdrawn.");
        }
    }
}
