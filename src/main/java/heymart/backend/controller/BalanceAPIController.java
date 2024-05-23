package heymart.backend.controller;

import heymart.backend.models.Balance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import heymart.backend.service.BalanceServiceImpl;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/balance/api")
public class BalanceAPIController {
    
    private final BalanceServiceImpl balanceService;

    public BalanceAPIController(BalanceServiceImpl balanceService) {
        this.balanceService = balanceService;
    }

    private static final String FREQUENTLY_USED_STRING = "Balance with ownerId ";
    
    @GetMapping("/getBalance")
    public ResponseEntity<?> getBalanceById(@RequestParam Long ownerId) {
        if (balanceService.existsById(ownerId)) {
            return ResponseEntity.ok(balanceService.getBalanceById(ownerId));
        } else {
            return ResponseEntity
                .badRequest()
                .body(FREQUENTLY_USED_STRING + ownerId + " not found.");
        }
    }

    @PostMapping("/addNewBalance")
    public ResponseEntity<?> addNewBalance(@RequestBody Map<String, String> json) {
        long ownerId = Long.parseLong(json.get("ownerId"));
        if (balanceService.existsById(ownerId)) {
            return ResponseEntity
                .badRequest()
                .body(FREQUENTLY_USED_STRING + ownerId + " already exists.");
        } else {
            balanceService.addNewBalance(ownerId);
            return ResponseEntity.ok(FREQUENTLY_USED_STRING + ownerId + " added.");
        }
    }

    @DeleteMapping("/deleteBalance")
    public ResponseEntity<?> deleteBalance(@RequestBody Map<String, String> json) {
        long ownerId = Long.parseLong(json.get("ownerId"));
        if (balanceService.existsById(ownerId)) {
            balanceService.deleteBalance(ownerId);
            return ResponseEntity.ok(FREQUENTLY_USED_STRING + ownerId + " deleted.");
        } else {
            return ResponseEntity
                .badRequest()
                .body(FREQUENTLY_USED_STRING + ownerId + " not found.");
        }
    }

    @GetMapping("/getAllBalance")
    public CompletableFuture<ResponseEntity<Iterable<Balance>>> getAllBalance() {
        return balanceService.getAllBalance()
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
