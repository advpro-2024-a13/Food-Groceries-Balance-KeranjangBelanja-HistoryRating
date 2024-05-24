package heymart.backend.controller;

import heymart.backend.models.Rating;
import heymart.backend.service.RatingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingServiceImpl ratingService;

    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRatingById(@PathVariable Long id) {
        if (ratingService.existsById(id)) {
            Rating rating = ratingService.getRatingById(id);
            return ResponseEntity.ok(rating);
        } else {
            return ResponseEntity.badRequest().body("Rating with id " + id + " not found.");
        }
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> modifyRating(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        if (request.containsKey("rating") && request.containsKey("review")) {
            int rating = Integer.parseInt(request.get("rating").toString());
            String review = request.get("review").toString();

            Rating modifiedRating = ratingService.modifyRating(id, rating, review);
            if (modifiedRating != null) {
                return ResponseEntity.ok("Rating modified for id " + id);
            } else {
                return ResponseEntity.badRequest().body("Rating with id " + id + " not found.");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid request format. Rating and review are required.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewRating(@RequestBody Map<String, Object> request) {
        Long ownerId = Long.parseLong(request.get("ownerId").toString());
        Long marketId = Long.parseLong(request.get("marketId").toString());
        int rating = Integer.parseInt(request.get("rating").toString());
        String review = request.get("review").toString();

        Rating newRating = ratingService.addNewRating(ownerId, marketId, rating, review);
        return ResponseEntity.ok("New rating added with id: " + newRating.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Long id) {
        if (ratingService.existsById(id)) {
            ratingService.deleteRating(id);
            return ResponseEntity.ok("Rating with id " + id + " deleted.");
        } else {
            return ResponseEntity.badRequest().body("Rating with id " + id + " not found.");
        }
    }

    @GetMapping("/owner/{ownerId}")
    public CompletableFuture<ResponseEntity<List<Rating>>> findByOwnerId(@PathVariable Long ownerId) {
        return ratingService.findByOwnerId(ownerId)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/market/{marketId}")
    public CompletableFuture<ResponseEntity<List<Rating>>> findByMarketId(@PathVariable Long marketId) {
        return ratingService.findBySupermarketId(marketId)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/getAll")
    public CompletableFuture<ResponseEntity<List<Rating>>> getAllRatings() {
        return ratingService.getAllRatings()
                .thenApply(ResponseEntity::ok)
                .exceptionally(this::handleGetRatingException);
    }

    private ResponseEntity<List<Rating>> handleGetRatingException(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
    }

}