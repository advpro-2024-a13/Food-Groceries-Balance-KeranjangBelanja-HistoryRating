package heymart.backend.controller;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.service.KeranjangBelanjaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/keranjangbelanja/api")
public class KeranjangBelanjaAPIController {

    private final KeranjangBelanjaServiceImpl keranjangBelanjaService;

    public KeranjangBelanjaAPIController(KeranjangBelanjaServiceImpl keranjangBelanjaService) {
        this.keranjangBelanjaService = keranjangBelanjaService;
    }

    @PostMapping("/createKeranjangBelanja")
    public ResponseEntity<?> createKeranjangBelanja(@RequestBody Long userId) {
        try {
            KeranjangBelanja newKeranjangBelanja = keranjangBelanjaService.createKeranjangBelanja(userId);
            return new ResponseEntity<>(newKeranjangBelanja, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Keranjang belanja for userId " + userId + " cannot be created");
        }
    }

    @GetMapping("/findKeranjangBelanjaById")
    public ResponseEntity<?> findKeranjangBelanjaById(@RequestParam Long userId) {
        try {
            KeranjangBelanja keranjangBelanja = keranjangBelanjaService.findKeranjangBelanjaById(userId);
            return new ResponseEntity<>(keranjangBelanja, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Keranjang Belanja not found for userId: " + userId, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addProductToKeranjangBelanja")
    public ResponseEntity<?> addProductToKeranjangBelanja(
            @RequestParam Long userId,
            @RequestParam UUID productId) {
        try {
            KeranjangBelanja updatedKeranjangBelanja = keranjangBelanjaService.addProductToKeranjang(userId, productId);
            return new ResponseEntity<>(updatedKeranjangBelanja, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Keranjang Belanja not found for userId: " + userId, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/countTotalProductInKeranjang")
    public ResponseEntity<?> countTotalProductInKeranjang(@RequestBody HashMap<UUID, Integer> productMap) {
        try {
            Integer totalProducts = keranjangBelanjaService.countTotalProductInKeranjang(productMap);
            return new ResponseEntity<>(totalProducts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/clearKeranjangBelanja")
    public ResponseEntity<?> clearKeranjangBelanja(@RequestParam Long userId) {
        try {
            keranjangBelanjaService.clearKeranjangBelanja(userId);
            return new ResponseEntity<>("Keranjang Belanja cleared successfully for userId: " + userId, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Keranjang Belanja not found for userId: " + userId, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}