package heymart.backend.controller;

import heymart.backend.dto.GetUpdateCheckout;
import heymart.backend.enums.CheckoutStatus;
import heymart.backend.models.Checkout;
import heymart.backend.models.KeranjangBelanja;
import heymart.backend.service.CheckoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    public static final String FREQUENTLY_USED_STRING = "Checkout with Id ";

    private final CheckoutServiceImpl checkoutService;

    @Autowired
    public CheckoutController(CheckoutServiceImpl checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/createCheckout")
    public ResponseEntity<?> createNewCheckout(@RequestBody KeranjangBelanja keranjangBelanja, @RequestParam Long totalPrice, @RequestParam CheckoutStatus checkoutStatus) {
        Checkout newCheckout = Checkout.builder()
                .checkoutId(UUID.randomUUID())
                .keranjangBelanja(keranjangBelanja)
                .checkoutDate(new Date())
                .totalPrice(totalPrice)
                .checkoutStatus(checkoutStatus)
                .build();

        return check;
        if(savedCheckout == null){
            return ResponseEntity
                    .badRequest()
                    .body("Failed to create a new checkout.");
        }
        else{
            return ResponseEntity.ok(savedCheckout);
        }
    }


    @GetMapping("/getCheckoutById")
    public ResponseEntity<?> getCheckoutById(@RequestParam UUID checkoutId) {
        if(checkoutService.existsByCheckoutId(checkoutId)){
            return ResponseEntity.ok(checkoutService.getCheckoutById(checkoutId));
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(FREQUENTLY_USED_STRING + checkoutId + " not found.");
        }
    }

    @PutMapping("/updateCheckout")
    public ResponseEntity<?> updateCheckout(@RequestBody GetUpdateCheckout newCheckoutData) {
        UUID checkoutId = newCheckoutData.getCheckoutId();
        if(checkoutService.existsByCheckoutId(checkoutId)){
            Checkout checkoutToUpdate = new Checkout();
            checkoutToUpdate.setKeranjangBelanja(newCheckoutData.getKeranjangBelanja());
            checkoutToUpdate.setCheckoutDate(newCheckoutData.getCheckoutDate());
            checkoutToUpdate.setTotalPrice(newCheckoutData.getTotalPrice());
            checkoutToUpdate.setCheckoutStatus(newCheckoutData.getCheckoutStatus());

            Checkout updatedCheckout = checkoutService.updateCheckout(checkoutId, checkoutToUpdate);
            if (updatedCheckout != null) {
                return ResponseEntity.ok(FREQUENTLY_USED_STRING + checkoutId + " is updated.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(FREQUENTLY_USED_STRING + checkoutId + " not found.");
        }
    }

    @DeleteMapping("deleteCheckoutById")
    public ResponseEntity<?> deleteCheckoutById(@RequestParam UUID checkoutId) {
        if(checkoutService.existsByCheckoutId(checkoutId)){
            checkoutService.deleteCheckoutById(checkoutId);
            return ResponseEntity.ok(FREQUENTLY_USED_STRING + checkoutId + " is deleted.");
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(FREQUENTLY_USED_STRING + checkoutId + " not found.");
        }
    }

    @GetMapping("/getAllCheckout")
    public CompletableFuture<ResponseEntity<Iterable<Checkout>>> getAllCheckout() {
        return checkoutService.getAllCheckout()
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
