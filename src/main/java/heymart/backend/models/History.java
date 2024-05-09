package heymart.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class History {

    @OneToMany(mappedBy = "history")
    List<Product> purchases = new ArrayList<>();

    private Long ownerId;

    private Long marketId;

    @Id
    private Long id;

    private double totalSpent;

    public History() {
    }

    public History(Long ownerId, Long marketId, List<Product> purchases, double totalSpent) {
        this.ownerId = ownerId;
        this.marketId = marketId;
        this.purchases = purchases;
        this.totalSpent = totalSpent;
    }

    public void addPurchase(Product product) {
        purchases.add(product);
        totalSpent += product.getProductPrice();
    }
}
