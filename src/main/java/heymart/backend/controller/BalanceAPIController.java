package heymart.backend.controller;

import heymart.backend.models.Balance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import heymart.backend.service.BalanceServiceImpl;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/balance/api")
public class BalanceAPIController {
    
    private final BalanceServiceImpl balanceService;

    public BalanceAPIController(BalanceServiceImpl balanceService) {
        this.balanceService = balanceService;
    }

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
    public CompletableFuture<ResponseEntity<Iterable<Balance>>> getAllBalance() {
        return balanceService.getAllBalance()
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
