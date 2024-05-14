package heymart.backend.controller;

import heymart.backend.models.Rating;
import heymart.backend.service.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

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
    public ResponseEntity<?> addNewRating(@RequestBody HashMap<String, Object> request) {
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
}