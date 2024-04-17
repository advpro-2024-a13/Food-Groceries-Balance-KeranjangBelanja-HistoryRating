package heymart.backend.service;

import heymart.backend.model.KeranjangBelanja;
import heymart.backend.model.Product;
import heymart.backend.repository.KeranjangBelanjaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class KeranjangBelanjaServiceImplTest {

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
    }

    @Test
    void testCreateKeranjangBelanja(){
        KeranjangBelanja keranjangBelanja = keranjangBelanjaList.get(1);
        doReturn(keranjangBelanja).when(keranjangBelanjaRepository).save(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.createKeranjangBelanja(keranjangBelanja);
        verify(keranjangBelanjaRepository, times(1)).save(keranjangBelanja);
        assertEquals(keranjangBelanja.getOwnerId(), result.getOwnerId());
    }
}