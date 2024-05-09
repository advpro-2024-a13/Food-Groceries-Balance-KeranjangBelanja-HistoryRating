package heymart.backend.controller;

import heymart.backend.models.History;
import heymart.backend.models.Product;
import heymart.backend.service.HistoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    public ResponseEntity<?> addNewHistory(@RequestBody HashMap<String, Object> request) {
        Long ownerId = Long.parseLong(request.get("ownerId").toString());
        Long marketId = Long.parseLong(request.get("marketId").toString());

        @SuppressWarnings("unchecked")
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
}