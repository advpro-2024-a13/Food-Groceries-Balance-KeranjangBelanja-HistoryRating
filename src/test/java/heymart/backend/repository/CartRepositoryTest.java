package heymart.backend.repository;

import heymart.backend.models.Cart;
import heymart.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartRepositoryTest {

    KeranjangBelanjaRepository keranjangBelanjaRepository;
    List<Cart> cartList;
    private List<Product> products;

    @BeforeEach
    void setKeranjangBelanja() {
        cartList = new ArrayList<>();
        keranjangBelanjaRepository = new KeranjangBelanjaRepository();
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

        Cart cart1 = new Cart("Divie_123", products);
        cartList.add(cart1);

        Cart cart2 = new Cart("Laras_123", products);
        cartList.add(cart2);

        Cart cart3 = new Cart("Vinka_123", products);
        cartList.add(cart3);
    }

    @Test
    void testSaveCreateKeranjangBelanja(){
        Cart cart = cartList.get(1);
        Cart result = keranjangBelanjaRepository.save(cart);

        Cart findResult = keranjangBelanjaRepository.findByOwnerId(cartList.get(1).getOwnerId());
        assertEquals(cart.getOwnerId(), result.getOwnerId());
        assertEquals(cart.getOwnerId(), findResult.getOwnerId());
        assertEquals(cart.getProducts(), result.getProducts());
        assertEquals(cart.getProducts(), findResult.getProducts());
    }
}