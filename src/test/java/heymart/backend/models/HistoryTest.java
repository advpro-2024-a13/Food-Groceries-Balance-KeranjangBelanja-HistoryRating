package heymart.backend.models;

import org.junit.jupiter.api.Test;

public class HistoryTest {
    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.mockito.Mockito.mock;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import heymart.backend.models.HistoryTest;

    public class HistoryTest {

        private History history;
        private Product product;
        private User user;
        private Supermarket supermarket;

        @BeforeEach
        public void setUp() {
            history = new History();
            product = mock(Product.class);
            user = mock(User.class);
            supermarket = mock(Supermarket.class);
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
            Product product1 = new Product("Product1", 10.0);
            Product product2 = new Product("Product2", 20.0);

            double total = product1.getPrice() + product2.getPrice();

            history.addPurchase(product1);
            history.addPurchase(product2);

            assertEquals(2, history.getPurchases().size());

            assertEquals(product1, history.getPurchases().get(0));
            assertEquals(product2, history.getPurchases().get(1));

            assertEquals(total, history.getTotalSpent());
        }


    }
}
