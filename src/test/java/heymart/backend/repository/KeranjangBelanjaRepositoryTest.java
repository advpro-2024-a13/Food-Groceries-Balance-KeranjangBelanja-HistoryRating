package heymart.backend.repository;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeranjangBelanjaRepositoryTest {

    KeranjangBelanjaRepository keranjangBelanjaRepository;
    List<KeranjangBelanja> keranjangBelanjaList;
    private List<Product> products;

    @BeforeEach
    void setKeranjangBelanja() {
        keranjangBelanjaList = new ArrayList<>();
        keranjangBelanjaRepository = new KeranjangBelanjaRepository();
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setSupermarketId(1648);
        product1.setProductId(123);
        product1.setProductName("Vanilla Bourbon");
        product1.setProductPrice(150);
        product1.setProductCategory("Gelato");
        product1.setProductAmount(2);

        Product product2 = new Product();
        product2.setSupermarketId(1748);
        product2.setProductId(234);
        product2.setProductName("Rum Raisin");
        product2.setProductPrice(200);
        product2.setProductCategory("Gelato");
        product2.setProductAmount(2);

        this.products.add(product1);
        this.products.add(product2);

        KeranjangBelanja keranjangBelanja1 = new KeranjangBelanja("Divie_123", products);
        keranjangBelanjaList.add(keranjangBelanja1);

        KeranjangBelanja keranjangBelanja2 = new KeranjangBelanja("Laras_123", products);
        keranjangBelanjaList.add(keranjangBelanja2);

        KeranjangBelanja keranjangBelanja3 = new KeranjangBelanja("Vinka_123", products);
        keranjangBelanjaList.add(keranjangBelanja3);
    }

    @Test
    void testSaveCreateKeranjangBelanja(){
        KeranjangBelanja keranjangBelanja = keranjangBelanjaList.get(1);
        KeranjangBelanja result = keranjangBelanjaRepository.save(keranjangBelanja);

        KeranjangBelanja findResult = keranjangBelanjaRepository.findByOwnerId(keranjangBelanjaList.get(1).getOwnerId());
        assertEquals(keranjangBelanja.getOwnerId(), result.getOwnerId());
        assertEquals(keranjangBelanja.getOwnerId(), findResult.getOwnerId());
        assertEquals(keranjangBelanja.getProducts(), result.getProducts());
        assertEquals(keranjangBelanja.getProducts(), findResult.getProducts());
    }
}