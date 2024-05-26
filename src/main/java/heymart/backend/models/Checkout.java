package heymart.backend.models;

import heymart.backend.enums.CheckoutStatus;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "checkout")
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID checkoutId;

    @OneToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "ownerId")
    private KeranjangBelanja keranjangBelanja;

    @Column(name = "checkout_date")
    private Date checkoutDate;

    @Column(name = "total_price")
    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "checkout_status")
    private CheckoutStatus checkoutStatus;
}