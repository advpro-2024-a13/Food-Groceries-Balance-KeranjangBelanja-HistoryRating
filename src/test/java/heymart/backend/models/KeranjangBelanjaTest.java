package heymart.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class KeranjangBelanjaTest {
    private KeranjangBelanja keranjangBelanja;
    private HashMap<Product, Integer> productMap;

    @BeforeEach
    void setUp(){
        keranjangBelanja = new KeranjangBelanja();
        productMap = new HashMap<>();

        Product product1 = new Product.Builder()
                .supermarketId(1648L)
                .productId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .productImagePath("https://images.tokopedia.net/img/cache/700/product-1/2020/3/30/75782982/75782982_8ff30a43-58f0-42a1-b762-18f014876acb_700_700")
                .productName("Ayam Potong")
                .productPrice(150)
                .productCategory("Bahan Makanan")
                .productAmount(2)
                .build();


        Product product2 = new Product.Builder()
                .supermarketId(1645L)
                .productId(UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c94"))
                .productImagePath("https://asset.kompas.com/crops/o6zdc0_3Z-kxUrW8nYaKeglbojo=/0x28:1000x695/750x500/data/photo/2021/07/14/60ee4d217d0f3.jpg")
                .productName("Daging Sapi")
                .productPrice(150)
                .productCategory("Bahan Makanan")
                .productAmount(2)
                .build();

        productMap.put(product1, 4);
        productMap.put(product2,3);

        keranjangBelanja.setProductMap(productMap);
    }

    @Test
    void testCreateKeranjangBelanjaEmptyProduct(){
        productMap.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            new KeranjangBelanja(productMap);
        });
    }

    @Test
    void testCreateKeranjangBelanjaSuccess(){
        assertEquals(2, keranjangBelanja.getProductMap().size());

        for (Product product : keranjangBelanja.getProductMap().keySet()) {
            if (product.getSupermarketId() == 1648L) {
                assertEquals("Ayam Potong", product.getProductName());
                assertEquals(150, product.getProductPrice());
                assertEquals("Bahan Makanan", product.getProductCategory());
                assertEquals(2, product.getProductAmount());
                assertEquals(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"), product.getProductId());
            } else if (product.getSupermarketId() == 1645L) {
                assertEquals("Daging Sapi", product.getProductName());
                assertEquals(150, product.getProductPrice());
                assertEquals("Bahan Makanan", product.getProductCategory());
                assertEquals(2, product.getProductAmount());
                assertEquals(UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c94"), product.getProductId());
            } else {
                fail("Unexpected product found in the productMap");
            }
        }
    }
}