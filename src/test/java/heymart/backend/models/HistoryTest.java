package heymart.backend.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HistoryTest {

    @Test
    public void testConstructor() {
        Long ownerId = 1L;
        Long marketId = 2L;
        List<Product> purchases = new ArrayList<>();
        double totalSpent = 100.0;

        History history = new History.Builder()
                .ownerId(ownerId)
                .marketId(marketId)
                .purchases(purchases)
                .totalSpent(totalSpent)
                .build();

        assertEquals(ownerId, history.getOwnerId());
        assertEquals(marketId, history.getMarketId());
        assertEquals(purchases, history.getPurchases());
        assertEquals(totalSpent, history.getTotalSpent(), 0.001);
    }

    @Test
    public void testAddPurchase() {
        History history = new History.Builder().build();
        Product product1 = new Product();
        product1.setProductPrice((long) 10.0);
        Product product2 = new Product();
        product2.setProductPrice((long) 20.0);

        history.addPurchase(product1);
        history.addPurchase(product2);

        assertEquals(2, history.getPurchases().size());
        assertEquals(product1, history.getPurchases().get(0));
        assertEquals(product2, history.getPurchases().get(1));
        assertEquals(30.0, history.getTotalSpent(), 0.001);
    }

    @Test
    public void testNullPurchase() {
        History history = new History.Builder().build();
        assertThrows(NullPointerException.class, () -> history.addPurchase(null));
    }
}