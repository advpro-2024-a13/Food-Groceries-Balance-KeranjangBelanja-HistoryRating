package heymart.backend.service;

import heymart.backend.enums.CheckoutStatus;
import heymart.backend.models.Checkout;
import heymart.backend.models.KeranjangBelanja;
import heymart.backend.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;

    @Autowired
    public CheckoutServiceImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public Checkout createCheckout(KeranjangBelanja keranjangBelanja, Long totalPrice, CheckoutStatus checkoutStatus) {
        Checkout checkout = Checkout.builder()
                .checkoutId(UUID.randomUUID())
                .keranjangBelanja(keranjangBelanja)
                .checkoutDate(new Date())
                .totalPrice(totalPrice)
                .checkoutStatus(checkoutStatus)
                .build();
        return checkoutRepository.save(checkout);
    }


    @Override
    public Checkout getCheckoutById(UUID checkoutId) {
        return checkoutRepository.findById(checkoutId).orElse(null);
    }

    @Override
    public Checkout updateCheckout(UUID checkoutId, Checkout newCheckoutData) {
        Checkout checkout = checkoutRepository.findById(checkoutId).orElse(null);
        if (checkout != null) {
            checkout.setKeranjangBelanja(newCheckoutData.getKeranjangBelanja());
            checkout.setCheckoutDate(newCheckoutData.getCheckoutDate());
            checkout.setTotalPrice(newCheckoutData.getTotalPrice());
            checkout.setCheckoutStatus(newCheckoutData.getCheckoutStatus());
            return checkoutRepository.save(checkout);
        }
        return null;
    }

    @Override
    public void deleteCheckoutById(UUID checkoutId) {
        Optional<Checkout> checkout = checkoutRepository.findById(checkoutId);
        if(checkout.isPresent()){
            checkoutRepository.deleteById(checkoutId);
        }
    }

    @Override
    public boolean existsByCheckoutId(UUID checkoutId){
        return checkoutRepository.existsById(checkoutId);
    }

    @Override
    public CompletableFuture<Iterable<Checkout>> getAllCheckout() {
        return CompletableFuture.supplyAsync(() -> checkoutRepository.findAll());
    }
}