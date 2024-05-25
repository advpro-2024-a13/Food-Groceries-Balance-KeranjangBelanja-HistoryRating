package heymart.backend.controller;

import heymart.backend.service.KeranjangBelanjaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keranjangbelanja")
public class KeranjangBelanjaController {

    private static final String FREQUENTLY_USED_STRING = "Keranjang Belanja with ownerId ";

    private final KeranjangBelanjaServiceImpl keranjangBelanjaService;

    @Autowired
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
//    @PutMapping("/addProductToKeranjangBelanja")
//    public KeranjangBelanja addProductToKeranjangBelanja(@PathVariable Long ownerId, @PathVariable UUID productId,
//                                                         @PathVariable int quantity){
//        return keranjangBelanjaService.addProductToKeranjangBelanja(ownerId, productId, quantity);
//    }
//
//    @PutMapping("/{ownerId}/removeProduct/{productId}")
//    public KeranjangBelanja removeProductFromKeranjangBelanja(@PathVariable Long ownerId, @PathVariable UUID productId){
//        return keranjangBelanjaService.removeProductFromKeranjangBelanja(ownerId, productId);
//    }

}