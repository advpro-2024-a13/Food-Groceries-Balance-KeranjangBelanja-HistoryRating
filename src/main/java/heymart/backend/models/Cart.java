package heymart.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@Table(name="keranjangBelanja", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Changed from Integer to Long

    @Column(name = "user_id")
    @NotNull
    private Long userId;  // Changed from Integer to Long

    @Column(name="supermarket_id")
    @NotNull
    private Long supermarketId;

    @Column(name = "product_id")
    @NotNull
    private Long productId;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    private int quantity;

    public Cart() {
    }

    public Cart(KeranjangBelanjaDto cartDto, Product product, Supermarket supermarket, Long userId) {
        this.userId = userId; // Constructor parameter type updated to Long
        this.supermarketId = cartDto.getSupermarketId();
        this.productId = cartDto.getProductId();
        this.quantity = cartDto.getQuantity();
        this.product = product;
        this.createdDate = new Date();
    }

    public Cart(@NotNull Long userId, @NotNull Long supermarketId, @NotNull Long productId, int quantity) {
        this.userId = userId;
        this.supermarketId = supermarketId;
        this.productId = productId;
        this.createdDate = new Date();
        this.quantity = quantity;
    }

    public Cart(KeranjangBelanjaDto cartDto, Product product) {
        this.userId = cartDto.getUserId(); // Assuming cartDto.getUserId() now returns Long
        this.productId = cartDto.getProductId();
        this.quantity = cartDto.getQuantity();
        this.product = product;
        this.createdDate = new Date();
    }
}