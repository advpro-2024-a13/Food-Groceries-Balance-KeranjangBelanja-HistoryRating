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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public void setTotal(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public double getTotal() {
        return totalSpent;
    }

    // Memento methods
    public HistoryMemento save() {
        return new HistoryMemento(ownerId, marketId, new ArrayList<>(purchases), totalSpent);
    }

    public void restore(HistoryMemento memento) {
        this.ownerId = memento.getOwnerId();
        this.marketId = memento.getMarketId();
        this.purchases = new ArrayList<>(memento.getPurchases());
        this.totalSpent = memento.getTotalSpent();
    }
}
