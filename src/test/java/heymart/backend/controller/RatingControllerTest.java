package heymart.backend.controller;

import heymart.backend.models.Rating;
import heymart.backend.service.RatingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

    @Mock
    private RatingServiceImpl ratingService;

    @InjectMocks
    private RatingController ratingController;

    @Test
    void testGetRatingById() {
        Long id = 1L;
        Rating rating = new Rating();
        when(ratingService.existsById(id)).thenReturn(true);
        when(ratingService.getRatingById(id)).thenReturn(rating);

        ResponseEntity<?> response = ratingController.getRatingById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rating, response.getBody());
    }

    @Test
    void testGetRatingByIdNotFound() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = ratingController.getRatingById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Rating with id " + id + " not found.", response.getBody());
    }

    @Test
    void testModifyRating() {
        Long id = 1L;
        int rating = 4;
        String review = "Great product!";
        Rating modifiedRating = new Rating(123L, 456L, rating, review);
        when(ratingService.modifyRating(id, rating, review)).thenReturn(modifiedRating);

        HashMap<String, Object> request = new HashMap<>();
        request.put("rating", rating);
        request.put("review", review);

        ResponseEntity<?> response = ratingController.modifyRating(id, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rating modified for id " + id, response.getBody());
    }


    @Test
    void testModifyRatingNotFound() {
        Long id = 1L;
        int rating = 4;
        String review = "Great product!";

        when(ratingService.modifyRating(id ,rating, review)).thenReturn(null);

        HashMap<String, Object> request = new HashMap<>();
        request.put("rating", rating);
        request.put("review", review);

        ResponseEntity<?> response = ratingController.modifyRating(id, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Rating with id " + id + " not found.", response.getBody());
    }


    @Test
    void testAddNewRating() {
        Long ownerId = 1L;
        Long marketId = 2L;
        int rating = 4;
        String review = "Great product!";
        Rating newRating = new Rating(ownerId, marketId, rating, review);
        when(ratingService.addNewRating(ownerId, marketId, rating, review)).thenReturn(newRating);

        HashMap<String, Object> request = new HashMap<>();
        request.put("ownerId", ownerId);
        request.put("marketId", marketId);
        request.put("rating", rating);
        request.put("review", review);

        ResponseEntity<?> response = ratingController.addNewRating(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New rating added with id: " + newRating.getId(), response.getBody());
    }

    @Test
    void testDeleteRating() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(true);

        ResponseEntity<?> response = ratingController.deleteRating(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rating with id " + id + " deleted.", response.getBody());
    }

    @Test
    void testDeleteRatingNotFound() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = ratingController.deleteRating(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Rating with id " + id + " not found.", response.getBody());
    }
}