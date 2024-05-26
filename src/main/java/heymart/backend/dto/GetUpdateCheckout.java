package heymart.backend.dto;

import heymart.backend.enums.CheckoutStatus;
import heymart.backend.models.KeranjangBelanja;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class GetUpdateCheckout {
    private UUID checkoutId;
    private KeranjangBelanja keranjangBelanja;
    private Date checkoutDate;
    private Long totalPrice;
    private CheckoutStatus checkoutStatus;
}