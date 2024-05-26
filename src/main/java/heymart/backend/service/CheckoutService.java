package heymart.backend.service;

import heymart.backend.enums.CheckoutStatus;
import heymart.backend.models.Checkout;
import heymart.backend.models.KeranjangBelanja;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CheckoutService {
    Checkout createCheckout(KeranjangBelanja keranjangBelanja, Long totalPrice, CheckoutStatus checkoutStatus);
    Checkout getCheckoutById(UUID checkoutId);
    Checkout updateCheckout(UUID checkoutId, Checkout newCheckoutData);
    void deleteCheckoutById(UUID checkoutId);
    boolean existsByCheckoutId(UUID checkoutId);
    CompletableFuture<Iterable<Checkout>> getAllCheckout();
}