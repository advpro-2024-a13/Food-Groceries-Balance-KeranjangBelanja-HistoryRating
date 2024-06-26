package heymart.backend.controller;

import heymart.backend.models.History;
import heymart.backend.models.Product;
import heymart.backend.service.HistoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryServiceImpl historyService;

    public HistoryController(HistoryServiceImpl historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getHistoryById(@PathVariable Long id) {
        if (historyService.existsById(id)) {
            History history = historyService.getHistoryById(id);
            return ResponseEntity.ok(history);
        } else {
            return ResponseEntity.badRequest().body("History with id " + id + " not found.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewHistory(@RequestBody Map<String, Object> request) {
        Long ownerId = Long.parseLong(request.get("ownerId").toString());
        Long marketId = Long.parseLong(request.get("marketId").toString());
        List<Product> purchases = (List<Product>) request.get("purchases");
        double totalSpent = Double.parseDouble(request.get("totalSpent").toString());

        History newHistory = historyService.addNewHistory(ownerId, marketId, purchases, totalSpent);
        return ResponseEntity.ok("New history added with id: " + newHistory.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHistory(@PathVariable Long id) {
        if (historyService.existsById(id)) {
            historyService.deleteHistory(id);
            return ResponseEntity.ok("History with id " + id + " deleted.");
        } else {
            return ResponseEntity.badRequest().body("History with id " + id + " not found.");
        }
    }

    @GetMapping("/getAll")
    public CompletableFuture<ResponseEntity<List<History>>> getAllHistory() {
        return historyService.getAllHistory()
                .thenApply(ResponseEntity::ok)
                .exceptionally(this::handleException);
    }

    @GetMapping("/owner/{ownerId}")
    public CompletableFuture<ResponseEntity<List<History>>> getHistoryByOwnerId(@PathVariable Long ownerId) {
        return historyService.getHistoryByOwnerId(ownerId)
                .thenApply(ResponseEntity::ok)
                .exceptionally(this::handleException);
    }

    @GetMapping("/market/{marketId}")
    public CompletableFuture<ResponseEntity<List<History>>> getHistoryByMarketId(@PathVariable Long marketId) {
        return historyService.getHistoryByMarketId(marketId)
                .thenApply(ResponseEntity::ok)
                .exceptionally(this::handleException);
    }

    ResponseEntity<List<History>> handleException(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
    }
}
