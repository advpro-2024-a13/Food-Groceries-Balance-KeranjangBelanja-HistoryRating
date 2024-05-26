package heymart.backend.controller;

import heymart.backend.command.*;
import heymart.backend.models.Rating;
import heymart.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    private CommandInvoker commandInvoker = new CommandInvoker();

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
    public ResponseEntity<?> modifyRating(@PathVariable Long id, @RequestBody HashMap<String, Object> request) {
        if (request.containsKey("rating") && request.containsKey("review") && ratingService.existsById(id)) {
            int rating = Integer.parseInt(request.get("rating").toString());
            String review = request.get("review").toString();

            ModifyRatingCommand command = new ModifyRatingCommand(ratingService, id, rating, review);
            commandInvoker.setCommand(command);
            commandInvoker.executeCommand();

            return ResponseEntity.ok("Rating modified for id " + id);
        } else {
            return ResponseEntity.badRequest().body("Id Not Found or Invalid request format. Rating and review are required.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewRating(@RequestBody HashMap<String, Object> request) {
        Long ownerId = Long.parseLong(request.get("ownerId").toString());
        Long marketId = Long.parseLong(request.get("marketId").toString());
        int rating = Integer.parseInt(request.get("rating").toString());
        String review = request.get("review").toString();

        AddNewRatingCommand command = new AddNewRatingCommand(ratingService, ownerId, marketId, rating, review);
        commandInvoker.setCommand(command);
        commandInvoker.executeCommand();

        return ResponseEntity.ok("New rating added.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Long id) {
        if (ratingService.existsById(id)) {
            DeleteRatingCommand command = new DeleteRatingCommand(ratingService, id);
            commandInvoker.setCommand(command);
            commandInvoker.executeCommand();

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
        return ratingService.findByMarketId(marketId)
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
