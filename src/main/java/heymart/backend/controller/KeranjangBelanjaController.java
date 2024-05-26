package heymart.backend.controller;

import heymart.backend.dto.GetProductRequest;
import heymart.backend.dto.GetUpdateRequest;
import heymart.backend.models.KeranjangBelanja;
import heymart.backend.service.KeranjangBelanjaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keranjangbelanja")
public class KeranjangBelanjaController {

    public static final String FREQUENTLY_USED_STRING = "Keranjang Belanja with ownerId ";

    private final KeranjangBelanjaServiceImpl keranjangBelanjaService;

    public KeranjangBelanjaController(KeranjangBelanjaServiceImpl keranjangBelanjaService){
        this.keranjangBelanjaService = keranjangBelanjaService;
    }

    @GetMapping("/getKeranjangBelanjaById")
    public ResponseEntity<?> getKeranjangBelanjaByOwnerId(@RequestParam Long ownerId){
        if(keranjangBelanjaService.existsByOwnerId(ownerId)){
            return ResponseEntity.ok(keranjangBelanjaService.getKeranjangBelanjaByOwnerId(ownerId));
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(FREQUENTLY_USED_STRING + ownerId + " not found.");
        }
    }

    @PostMapping("/createNewKeranjangBelanja")
    public ResponseEntity<?> createNewKeranjangBelanja(@RequestParam Long ownerId){
        if(keranjangBelanjaService.existsByOwnerId(ownerId)){
            return ResponseEntity
                    .badRequest()
                    .body(FREQUENTLY_USED_STRING + ownerId + " already exists.");
        }
        else{
            keranjangBelanjaService.createNewKeranjangBelanja(ownerId);
            return ResponseEntity.ok(FREQUENTLY_USED_STRING + ownerId + " created.");
        }
    }

    @PutMapping("/updateKeranjangBelanja")
    public ResponseEntity<?> updateKeranjangBelanja(@RequestBody GetUpdateRequest updateRequest) {
        Long ownerId = updateRequest.getOwnerId();
        if(keranjangBelanjaService.existsByOwnerId(ownerId)){
            KeranjangBelanja keranjangBelanja = keranjangBelanjaService.updateKeranjangBelanja(
                    ownerId,
                    updateRequest.getUpdatedProducts());
            return ResponseEntity.ok(FREQUENTLY_USED_STRING + ownerId + " is updated.");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(FREQUENTLY_USED_STRING + ownerId + " not found.");
        }
    }

    @DeleteMapping("/clearKeranjangBelanja")
    public ResponseEntity<?> clearKeranjangBelanja(@RequestParam Long ownerId){
        if(keranjangBelanjaService.existsByOwnerId(ownerId)){
            keranjangBelanjaService.clearKeranjangBelanja(ownerId);
            return ResponseEntity.ok(FREQUENTLY_USED_STRING + ownerId + " is deleted.");
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(FREQUENTLY_USED_STRING + ownerId + " not found.");
        }
    }
    @PostMapping("/addProductToKeranjangBelanja")
    public ResponseEntity<?> addProductToKeranjangBelanja(@RequestBody GetProductRequest productRequest){
        Long ownerId = productRequest.getOwnerId();
        if(keranjangBelanjaService.existsByOwnerId(ownerId)){
            KeranjangBelanja keranjangBelanja = keranjangBelanjaService.addProductToKeranjangBelanja(
                    ownerId,
                    productRequest.getProductId(),
                    productRequest.getQuantity());
            return ResponseEntity.ok(keranjangBelanja);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(FREQUENTLY_USED_STRING + ownerId + " not found.");
        }
    }

    @DeleteMapping("/removeProductFromKeranjangBelanja")
    public ResponseEntity<?> removeProductFromKeranjangBelanja(@RequestBody GetProductRequest productRequest){
        Long ownerId = productRequest.getOwnerId();
        if(keranjangBelanjaService.existsByOwnerId(ownerId)){
            KeranjangBelanja keranjangBelanja = keranjangBelanjaService.removeProductFromKeranjangBelanja(
                    ownerId,
                    productRequest.getProductId());
            return ResponseEntity.ok(keranjangBelanja);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(FREQUENTLY_USED_STRING + ownerId + " not found.");
        }
    }
}