package heymart.backend.service;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.repository.KeranjangBelanjaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KeranjangBelanjaServiceImplTest {
    @InjectMocks
    KeranjangBelanjaServiceImpl keranjangBelanjaService;
    @Mock
    KeranjangBelanjaRepository keranjangBelanjaRepository;

    private KeranjangBelanja keranjangBelanja;

    @BeforeEach
    void setUp() {
        Long ownerId = 1L;
        HashMap<UUID, Integer> products = new HashMap<>();
        products.put(UUID.randomUUID(), 5);

        keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setOwnerId(ownerId);
        keranjangBelanja.setProducts(products);
    }

    @Test
    void testCreateNewKeranjangBelanja(){
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.createNewKeranjangBelanja(keranjangBelanja.getOwnerId());

        assertEquals(keranjangBelanja.getOwnerId(), result.getOwnerId());
        assertEquals(keranjangBelanja.getProducts(), result.getProducts());
    }

    @Test
    void testUpdateKeranjangBelanja(){
        when(keranjangBelanjaRepository.findById(keranjangBelanja.getOwnerId())).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.updateKeranjangBelanja(keranjangBelanja.getOwnerId(), keranjangBelanja.getProducts());

        assertEquals(keranjangBelanja.getOwnerId(), result.getOwnerId());
        assertEquals(keranjangBelanja.getProducts(), result.getProducts());
    }

    @Test
    void testAddProductToKeranjangBelanja(){
        UUID productId = UUID.randomUUID();
        int quantity = 5;

        when(keranjangBelanjaRepository.findById(keranjangBelanja.getOwnerId())).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.addProductToKeranjangBelanja(keranjangBelanja.getOwnerId(), productId, quantity);

        assertEquals(keranjangBelanja.getOwnerId(), result.getOwnerId());
        assertEquals(quantity, result.getProducts().get(productId));
    }

    @Test
    void testRemoveProductFromKeranjangBelanja(){
        UUID productId = keranjangBelanja.getProducts().keySet().iterator().next();

        when(keranjangBelanjaRepository.findById(keranjangBelanja.getOwnerId())).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.removeProductFromKeranjangBelanja(keranjangBelanja.getOwnerId(), productId);

        assertEquals(keranjangBelanja.getOwnerId(), result.getOwnerId());
        assertFalse(result.getProducts().containsKey(productId));
    }

    @Test
    void testClearKeranjangBelanja(){
        when(keranjangBelanjaRepository.findById(keranjangBelanja.getOwnerId())).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(null);

        keranjangBelanjaService.clearKeranjangBelanja(keranjangBelanja.getOwnerId());

        verify(keranjangBelanjaRepository, times(1)).save(any(KeranjangBelanja.class));
        assertTrue(keranjangBelanja.getProducts().isEmpty());
    }

    @Test
    void testExistsByOwnerId(){
        when(keranjangBelanjaRepository.existsById(keranjangBelanja.getOwnerId())).thenReturn(true);

        boolean exists = keranjangBelanjaService.existsByOwnerId(keranjangBelanja.getOwnerId());

        assertTrue(exists);
    }

    @Test
    void testGetKeranjangBelanjaByOwnerId(){
        when(keranjangBelanjaRepository.findById(keranjangBelanja.getOwnerId())).thenReturn(Optional.of(keranjangBelanja));

        KeranjangBelanja result = keranjangBelanjaService.getKeranjangBelanjaByOwnerId(keranjangBelanja.getOwnerId());

        assertEquals(keranjangBelanja.getOwnerId(), result.getOwnerId());
        assertEquals(keranjangBelanja.getProducts(), result.getProducts());
    }
}