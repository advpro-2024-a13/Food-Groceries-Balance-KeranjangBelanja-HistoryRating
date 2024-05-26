package heymart.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "keranjangBelanja_ownerId")
    private KeranjangBelanja keranjangBelanja;

    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    private String productName;
    private long productPrice;
    private String productCategory;
    private int productAmount;
    private Long supermarketId;
    private Long productId;

    public Product(Long id, String name, int price) {
        this.id = id;
        this.productName = name;
        this.productPrice = price;
    }

    public Product() {
    }
}
