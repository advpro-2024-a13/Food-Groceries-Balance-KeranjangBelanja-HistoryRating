package heymart.backend.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

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
    public void testAddPurchase() {
        Product product = new Product();
        history.addPurchase(product);
        assertEquals(1, history.getPurchases().size());
        assertEquals(product, history.getPurchases().getFirst());
    }
}
