package heymart.backend.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private List<Product> products;

    @BeforeEach
    void setKeranjangBelanja(){
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setSupermarketId(1648L);
        product1.setProductId(123L);
        product1.setProductName("Vanilla Bourbon");
        product1.setProductPrice(150);
        product1.setProductCategory("Gelato");
        product1.setProductAmount(2);

        Product product2 = new Product();
        product2.setSupermarketId(1748L);
        product2.setProductId(234L);
        product2.setProductName("Rum Raisin");
        product2.setProductPrice(200);
        product2.setProductCategory("Gelato");
        product2.setProductAmount(2);

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateKeranjangBelanjaEmptyProduct(){
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            new Cart("rxa15", this.products);
        });
    }

    @Test
    void testCreateKeranjangBelanjaSuccess(){
        Cart cart = new Cart("rxa15", this.products);

        assertSame(this.products, cart.getProducts());
        assertEquals(2, cart.getProducts().size());

        assertEquals(1648, cart.getProducts().get(0).getSupermarketId());
        assertEquals(123, cart.getProducts().get(0).getProductId());
        assertEquals("Vanilla Bourbon", cart.getProducts().get(0).getProductName());
        assertEquals(150, cart.getProducts().get(0).getProductPrice());
        assertEquals("Gelato", cart.getProducts().get(0).getProductCategory());
        assertEquals(2, cart.getProducts().get(0).getProductAmount());

        assertEquals(1748, cart.getProducts().get(1).getSupermarketId());
        assertEquals(234, cart.getProducts().get(1).getProductId());
        assertEquals("Rum Raisin", cart.getProducts().get(1).getProductName());
        assertEquals(200, cart.getProducts().get(1).getProductPrice());
        assertEquals("Gelato", cart.getProducts().get(1).getProductCategory());
        assertEquals(2, cart.getProducts().get(1).getProductAmount());
    }
}
