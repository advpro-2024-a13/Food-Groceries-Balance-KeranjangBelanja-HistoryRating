package heymart.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class History {

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> purchases = new ArrayList<>();

    private Long ownerId;
    private Long marketId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double totalSpent;

    public History() {}

    private History(Builder builder) {
        this.ownerId = builder.ownerId;
        this.marketId = builder.marketId;
        this.purchases = builder.purchases;
        this.totalSpent = builder.totalSpent;
    }

    public void addPurchase(Product product) {
        purchases.add(product);
        product.setHistory(this);
        totalSpent += product.getProductPrice();
    }

    public static class Builder {
        private Long ownerId;
        private Long marketId;
        private List<Product> purchases = new ArrayList<>();
        private double totalSpent;

        public Builder ownerId(Long ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder marketId(Long marketId) {
            this.marketId = marketId;
            return this;
        }

        public Builder purchases(List<Product> purchases) {
            this.purchases = purchases;
            return this;
        }

        public Builder totalSpent(double totalSpent) {
            this.totalSpent = totalSpent;
            return this;
        }

        public History build() {
            return new History(this);
        }
    }
}
