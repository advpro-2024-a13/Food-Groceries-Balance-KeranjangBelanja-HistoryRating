package heymart.backend.repository;

import heymart.backend.models.KeranjangBelanja;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class KeranjangBelanjaRepositoryTest {

    @Mock
    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCreateKeranjangBelanja(){
        Long ownerId = 1L;
        HashMap<UUID, Integer> products = new HashMap<>();
        products.put(UUID.randomUUID(), 5);

        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setOwnerId(ownerId);
        keranjangBelanja.setProducts(products);

        when(keranjangBelanjaRepository.save(keranjangBelanja)).thenReturn(keranjangBelanja);
        when(keranjangBelanjaRepository.findById(ownerId)).thenReturn(Optional.of(keranjangBelanja));

        KeranjangBelanja result = keranjangBelanjaRepository.save(keranjangBelanja);
        KeranjangBelanja findResult = keranjangBelanjaRepository.findById(ownerId).orElse(null);

        assertEquals(keranjangBelanja.getOwnerId(), result.getOwnerId());
        assertEquals(keranjangBelanja.getOwnerId(), findResult.getOwnerId());
        assertEquals(keranjangBelanja.getProducts(), result.getProducts());
        assertEquals(keranjangBelanja.getProducts(), findResult.getProducts());
    }
}