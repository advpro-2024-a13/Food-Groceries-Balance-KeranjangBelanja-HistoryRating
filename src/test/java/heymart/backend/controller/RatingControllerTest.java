package heymart.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {

    @Mock
    private RatingServiceImpl ratingService;

    @InjectMocks
    private RatingController ratingController;

    @Test
    public void testGetRatingById() {
        Long id = 1L;
        Rating rating = new Rating();
        when(ratingService.existsById(id)).thenReturn(true);
        when(ratingService.getRatingById(id)).thenReturn(rating);

        ResponseEntity<?> response = ratingController.getRatingById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rating, response.getBody());
    }

    @Test
    public void testGetRatingByIdNotFound() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = ratingController.getRatingById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Rating with id " + id + " not found.", response.getBody());
    }

    @Test
    public void testModifyRating() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(true);

        ResponseEntity<?> response = ratingController.getRatingById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testModifyRating_InvalidRequest() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = ratingController.getRatingById(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAddNewRating() {
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
        assertEquals("New rating added.", response.getBody());
    }

    @Test
    public void testDeleteRating() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(true);

        ResponseEntity<?> response = ratingController.deleteRating(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rating with id " + id + " deleted.", response.getBody());
    }

    @Test
    public void testDeleteRatingNotFound() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = ratingController.deleteRating(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Rating with id " + id + " not found.", response.getBody());
    }
}