package heymart.backend.models;

import jakarta.persistence.*;
import lombok.*;

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
    private Long SupermarketId;
    private Long ProductId;

    public Product(Long id, String name, int price) {
        this.id = id;
        this.productName = name;
        this.productPrice = price;
    }

    public Product() {
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
