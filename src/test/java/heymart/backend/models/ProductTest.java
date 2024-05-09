package heymart.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product product;
    private KeranjangBelanja keranjangBelanja;
    private History history;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.keranjangBelanja = new KeranjangBelanja();
        this.history = new History();
    }

    @Test
    void testSetId() {
        long id = 1L;
        product.setId(id);
        assertEquals(id, product.getId());
    }

    @Test
    void testSetKeranjangBelanja() {
        product.setKeranjangBelanja(keranjangBelanja);
        assertEquals(keranjangBelanja, product.getKeranjangBelanja());
    }

    @Test
    void testSetHistory() {
        product.setHistory(history);
        assertEquals(history, product.getHistory());
    }

    @Test
    void testGetId() {
        long id = 1L;
        product.setId(id);
        assertEquals(id, product.getId());
    }

    @Test
    void testGetKeranjangBelanja() {
        product.setKeranjangBelanja(keranjangBelanja);
        assertEquals(keranjangBelanja, product.getKeranjangBelanja());
    }

    @Test
    void testGetHistory() {
        product.setHistory(history);
        assertEquals(history, product.getHistory());
    }

    @Test
    void testGetProductName() {
        String productName = "Product Name";
        product.setProductName(productName);
        assertEquals(productName, product.getProductName());
    }

    @Test
    void testGetProductCategory() {
        String productCategory = "Product Category";
        product.setProductCategory(productCategory);
        assertEquals(productCategory, product.getProductCategory());
    }

    @Test
    void testGetProductAmount() {
        int productAmount = 1;
        product.setProductAmount(productAmount);
        assertEquals(productAmount, product.getProductAmount());
    }

    @Test
    void testGetSupermarketId() {
        Long supermarketId = 1L;
        product.setSupermarketId(supermarketId);
        assertEquals(supermarketId, product.getSupermarketId());
    }

    @Test
    void testGetProductId() {
        Long productId = 1L;
        product.setProductId(productId);
        assertEquals(productId, product.getProductId());
    }
}