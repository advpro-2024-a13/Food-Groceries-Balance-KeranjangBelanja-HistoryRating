package heymart.backend.controller;

import heymart.backend.models.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import heymart.backend.service.BalanceServiceImpl;

import java.util.HashMap;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    
    @Autowired
    private BalanceServiceImpl balanceService;

    @PostMapping("/modifyBalance")
    public ResponseEntity<?> modifyBalance(@RequestBody HashMap<String, String> JSON) {
        long ownerId = Long.parseLong(JSON.get("ownerId"));
        long balance = Long.parseLong(JSON.get("amount"));
        if (balanceService.existsById(ownerId)) {
            balanceService.modifyBalance(ownerId, balance);
            return ResponseEntity.ok("Balance with ownerId " + ownerId + " modified.");
        } else {
            return ResponseEntity
                .badRequest()
                .body("Balance with ownerId " + ownerId + " not found.");
        }
    }

    @GetMapping("/getBalance")
    public ResponseEntity<?> getBalanceById(@RequestBody HashMap<String, String> JSON) {
        long ownerId = Long.parseLong(JSON.get("ownerId"));
        if (balanceService.existsById(ownerId)) {
            return ResponseEntity.ok(balanceService.getBalanceById(ownerId).getBalance());
        } else {
            return ResponseEntity
                .badRequest()
                .body("Balance with ownerId " + ownerId + " not found.");
        }
    }

    @PostMapping("/addNewBalance")
    public ResponseEntity<?> addNewBalance(@RequestBody HashMap<String, String> JSON) {
        long ownerId = Long.parseLong(JSON.get("ownerId"));
        if (balanceService.existsById(ownerId)) {
            return ResponseEntity
                .badRequest()
                .body("Balance with ownerId " + ownerId + " already exists.");
        } else {
            balanceService.addNewBalance(ownerId);
            return ResponseEntity.ok("Balance with ownerId " + ownerId + " added.");
        }
    }

    @DeleteMapping("/deleteBalance")
    public ResponseEntity<?> deleteBalance(@RequestBody HashMap<String, String> JSON) {
        long ownerId = Long.parseLong(JSON.get("ownerId"));
        if (balanceService.existsById(ownerId)) {
            balanceService.deleteBalance(ownerId);
            return ResponseEntity.ok("Balance with ownerId " + ownerId + " deleted.");
        } else {
            return ResponseEntity
                .badRequest()
                .body("Balance with ownerId " + ownerId + " not found.");
        }
    }

    @PostMapping("/topUp")
    public ResponseEntity<?> topUp(@RequestBody HashMap<String, String> JSON) {
        long ownerId = Long.parseLong(JSON.get("ownerId"));
        long amount = Long.parseLong(JSON.get("amount"));
        String role = JSON.get("role");
        if (balanceService.existsById(ownerId) && role.equals("ROLE_PEMBELI")) {
            Balance balance = balanceService.getBalanceById(ownerId);
            if (balance == null) {
                balanceService.addNewBalance(ownerId);
                balance = balanceService.getBalanceById(ownerId);
            }
            long newBalance = balance.getBalance() == null ? 0L : balance.getBalance();
            balanceService.modifyBalance(ownerId, newBalance + amount);
            return ResponseEntity.ok("Balance with ownerId " + ownerId + " topped up.");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Param Invalid");
        }
    }


    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody HashMap<String, String> JSON) {
        long ownerId = Long.parseLong(JSON.get("ownerId"));
        long amount = Long.parseLong(JSON.get("amount"));
        String role = JSON.get("role");
        if (balanceService.existsById(ownerId) && role.equals("ROLE_PENGELOLA")) {
            Balance balance = balanceService.getBalanceById(ownerId);
            if (balance == null) {
                balanceService.addNewBalance(ownerId);
                balance = balanceService.getBalanceById(ownerId);
            }
            long newBalance = balance.getBalance() == null ? 0L : balance.getBalance();
            balanceService.modifyBalance(ownerId, newBalance - amount);
            return ResponseEntity.ok("Balance with ownerId " + ownerId + " withdrawn.");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Param Invalid");
        }
    }
}
