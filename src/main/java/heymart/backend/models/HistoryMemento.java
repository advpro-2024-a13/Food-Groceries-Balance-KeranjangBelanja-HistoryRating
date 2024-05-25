package heymart.backend.models;

import java.util.List;

public class HistoryMemento {
    private final Long ownerId;
    private final Long marketId;
    private final List<Product> purchases;
    private final double totalSpent;

    public HistoryMemento(Long ownerId, Long marketId, List<Product> purchases, double totalSpent) {
        this.ownerId = ownerId;
        this.marketId = marketId;
        this.purchases = purchases;
        this.totalSpent = totalSpent;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public List<Product> getPurchases() {
        return purchases;
    }

    public double getTotalSpent() {
        return totalSpent;
    }
}
