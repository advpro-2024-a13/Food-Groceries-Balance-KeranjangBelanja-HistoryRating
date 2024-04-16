package heymart.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KeranjangBelanjaTest {
    private List<Product> products;

    @BeforeEach
    void setKeranjangBelanja(){
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductSupermarket("Indomales");
        product1.setProductId("123");
        product1.setProductName("Vanilla Bourbon");
        product1.setProductPrice(150);
        product1.setProductCategory("Gelato");
        product1.setProductAmount(2);

        Product product2 = new Product();
        product2.setProductSupermarket("Indorajin");
        product2.setProductId("234");
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
            KeranjangBelanja keranjangBelanja = new KeranjangBelanja("rxa15", this.products);
        });
    }

    @Test
    void testCreateKeranjangBelanjaSuccess(){
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja("rxa15", this.products);

        assertSame(this.products, keranjangBelanja.getProducts());
        assertEquals(2, keranjangBelanja.getProducts().size());

        assertEquals("Indomales", keranjangBelanja.getProducts().get(0).getProductSupermarket());
        assertEquals("123", keranjangBelanja.getProducts().get(0).getProductId());
        assertEquals("Vanilla Bourbon", keranjangBelanja.getProducts().get(0).getProductName());
        assertEquals(150, keranjangBelanja.getProducts().get(0).getProductPrice());
        assertEquals("Gelato", keranjangBelanja.getProducts().get(0).getProductCategory());
        assertEquals(2, keranjangBelanja.getProducts().get(0).getProductAmount());

        assertEquals("Indorajin", keranjangBelanja.getProducts().get(1).getProductSupermarket());
        assertEquals("234", keranjangBelanja.getProducts().get(1).getProductId());
        assertEquals("Rum Raisin", keranjangBelanja.getProducts().get(1).getProductName());
        assertEquals(200, keranjangBelanja.getProducts().get(1).getProductPrice());
        assertEquals("Gelato", keranjangBelanja.getProducts().get(1).getProductCategory());
        assertEquals(2, keranjangBelanja.getProducts().get(1).getProductAmount());
    }
}
