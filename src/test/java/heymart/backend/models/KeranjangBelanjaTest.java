package heymart.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KeranjangBelanjaTest {
    private List<Product> products;
    private KeranjangBelanja.KeranjangBelanjaBuilder builder;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        this.products.add(new Product());
        this.builder = KeranjangBelanja.builder();
    }

    @Test
    void testBuilder() {
        KeranjangBelanja keranjangBelanja = builder.ownerId("rxa15").products(products).build();

        assertNotNull(keranjangBelanja);
        assertEquals("rxa15", keranjangBelanja.getOwnerId());
        assertEquals(products, keranjangBelanja.getProducts());
    }

    @Test
    void testToString() {
        KeranjangBelanja keranjangBelanja = builder.ownerId("rxa15").products(products).build();

        String expected = "KeranjangBelanja(ownerId=rxa15, products=" + products.toString() + ")";
        assertEquals(expected, keranjangBelanja.toString());
    }

    @Test
    void testOwnerId() {
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja("rxa15", products);

        assertEquals("rxa15", keranjangBelanja.getOwnerId());
    }

    @Test
    void testProducts() {
        KeranjangBelanja keranjangBelanja = builder.ownerId("rxa15").products(products).build();

        assertEquals(products, keranjangBelanja.getProducts());
    }

    @Test
    void testKeranjangBelanjaBuilder() {
        KeranjangBelanja.KeranjangBelanjaBuilder newBuilder = KeranjangBelanja.builder();

        assertNotNull(newBuilder);
    }

    @Test
    void testKeranjangBelanjaWithEmptyProducts() {
        assertThrows(IllegalArgumentException.class, () -> new KeranjangBelanja("rxa15", new ArrayList<>()));
    }

    @Test
    void testKeranjangBelanjaBuilderToString() {
        String expected = "KeranjangBelanja.KeranjangBelanjaBuilder(ownerId=rxa15, products=" + products.toString() + ")";
        assertEquals(expected, builder.ownerId("rxa15").products(products).toString());
    }

    @Test
    void testSetProducts() {
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setProducts(products);

        assertEquals(products, keranjangBelanja.getProducts());
    }
}