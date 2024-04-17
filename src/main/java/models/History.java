package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "history",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"owner_id", "market_id"})
        })

public class History{


    private Long ownerId;
    private Long marketId;
    private List<Product> purchases = new ArrayList<>();
    private double totalSpent;
    @Id
    private Long id;

    public History() {
    }

    public History(Long ownerId, Long marketId, List<Product> purchases, double totalSpent) {
        this.ownerId = ownerId;
        this.marketId = marketId;
        this.purchases = purchases;
        this.totalSpent = totalSpent;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    public Long getOwnerId() {
        return ownerId;
    }
    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }
    public Long getMarketId() {
        return marketId;
    }
    public void addPurchase(Product product) {
        purchases.add(product);
        totalSpent += product.getPrice();
    }
    public List<Product> getPurchases() {
        return purchases;
    }
    public double getTotalSpent() {
        return totalSpent;
    }
}
