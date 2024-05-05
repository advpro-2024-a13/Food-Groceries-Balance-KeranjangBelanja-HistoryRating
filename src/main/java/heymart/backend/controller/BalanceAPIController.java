package heymart.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import heymart.backend.service.BalanceServiceImpl;

import java.util.HashMap;

@RestController
@RequestMapping("/balance/api")
public class BalanceAPIController {
    
    @Autowired
    private BalanceServiceImpl balanceService;

    @GetMapping("/getBalance")
    public ResponseEntity<?> getBalanceById(@RequestParam Long ownerId) {
        if (balanceService.existsById(ownerId)) {
            return ResponseEntity.ok(balanceService.getBalanceById(ownerId));
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

    @GetMapping("/getAllBalance")
    public ResponseEntity<?> getAllBalance() {
        return ResponseEntity.ok(balanceService.getAllBalance());
    }
}
