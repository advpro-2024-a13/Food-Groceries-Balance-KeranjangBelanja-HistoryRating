package heymart.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryTest {

    private History history;

    @BeforeEach
    public void setUp() {
        history = new History();
    }

    @Test
    public void testGetSetId() {
        Long id = 123L;
        history.setId(id);
        assertEquals(id, history.getId());
    }

    @Test
    public void testGetSetOwnerId() {
        Long ownerId = 123L;
        history.setOwnerId(ownerId);
        assertEquals(ownerId, history.getOwnerId());
    }

    @Test
    public void testGetSetMarketId() {
        Long marketId = 456L;
        history.setMarketId(marketId);
        assertEquals(marketId, history.getMarketId());
    }

    @Test
    public void testGetSetTotalSpent() {
        double totalSpent = 100.0;
        history.setTotalSpent(totalSpent);
        assertEquals(totalSpent, history.getTotalSpent());
    }

    @Test
    public void testAddPurchase() {
        Product product = new Product();
        product.setProductPrice(50);
        history.addPurchase(product);
        assertEquals(1, history.getPurchases().size());
        assertEquals(product, history.getPurchases().getFirst());
        assertEquals(50.0, history.getTotalSpent());
    }

    @Test
    public void testGetSetPurchases() {
        List<Product> purchases = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        purchases.add(product1);
        purchases.add(product2);
        history.setPurchases(purchases);
        assertEquals(purchases, history.getPurchases());
    }
}