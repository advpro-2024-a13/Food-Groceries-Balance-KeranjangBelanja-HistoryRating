package heymart.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class KeranjangBelanjaTest {
    private KeranjangBelanja keranjangBelanja;
    private HashMap<UUID, Integer> productMap;

    @BeforeEach
    void setUp(){
        keranjangBelanja = new KeranjangBelanja();
        productMap = new HashMap<>();

        UUID product1Id = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");
        UUID product2Id = UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c94");

        productMap.put(product1Id, 4);
        productMap.put(product2Id,3);

        keranjangBelanja.setOwnerId(1L);
        keranjangBelanja.setProducts(productMap);
    }

    @Test
    void testCreateKeranjangBelanjaWithEmptyProducts(){
        productMap.clear();
        keranjangBelanja.setProducts(productMap);
        assertTrue(keranjangBelanja.getProducts().isEmpty());
    }

    @Test
    void testCreateKeranjangBelanjaSuccess(){
        assertEquals(2, keranjangBelanja.getProducts().size());

        for (UUID productId : keranjangBelanja.getProducts().keySet()) {
            if (productId.equals(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"))) {
                assertEquals(4, keranjangBelanja.getProducts().get(productId));
            } else if (productId.equals(UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c94"))) {
                assertEquals(3, keranjangBelanja.getProducts().get(productId));
            } else {
                fail("Unexpected product found in the productMap");
            }
        }
    }
}