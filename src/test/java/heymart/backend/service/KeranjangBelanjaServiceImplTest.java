package heymart.backend.service;

import heymart.backend.models.KeranjangBelanja;
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
public class KeranjangBelanjaServiceImplTest {
    @InjectMocks
    KeranjangBelanjaServiceImpl keranjangBelanjaService;
    @Mock
    KeranjangBelanjaRepository keranjangBelanjaRepository;
    private List<KeranjangBelanja> keranjangBelanjaList;

    @BeforeEach
    void setKeranjangBelanja() {
        keranjangBelanjaList = new ArrayList<>();

        List<Product> products = new ArrayList<>();

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

        products.add(product1);
        products.add(product2);

        KeranjangBelanja keranjangBelanja1 = new KeranjangBelanja("Divie_123", products);
        keranjangBelanjaList.add(keranjangBelanja1);

        KeranjangBelanja keranjangBelanja2 = new KeranjangBelanja("Laras_123", products);
        keranjangBelanjaList.add(keranjangBelanja2);

        KeranjangBelanja keranjangBelanja3 = new KeranjangBelanja("Vinka_123", products);
        keranjangBelanjaList.add(keranjangBelanja3);
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