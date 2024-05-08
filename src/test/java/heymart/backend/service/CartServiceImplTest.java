package heymart.backend.service;

import heymart.backend.models.Cart;
import heymart.backend.models.Product;
import heymart.backend.repository.KeranjangBelanjaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {
    @InjectMocks
    KeranjangBelanjaServiceImpl keranjangBelanjaService;
    @Mock
    KeranjangBelanjaRepository keranjangBelanjaRepository;
    private List<Cart> cartList;
    private List<Product> products;

    @BeforeEach
    void setKeranjangBelanja() {
        cartList = new ArrayList<>();

        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setSupermarketId(1648L);
        product1.setProductId(123L);
        product1.setProductName("Vanilla Bourbon");
        product1.setProductPrice(150);
        product1.setProductCategory("Gelato");
        product1.setProductAmount(2);

        Product product2 = new Product();
        product2.setSupermarketId(1748l);
        product2.setProductId(234l);
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
    void testCreateKeranjangBelanja(){
        Cart cart = cartList.get(1);
        doReturn(cart).when(keranjangBelanjaRepository).save(cart);

        Cart result = keranjangBelanjaService.createKeranjangBelanja(cart);
        verify(keranjangBelanjaRepository, times(1)).save(cart);
        assertEquals(cart.getOwnerId(), result.getOwnerId());
    }
}