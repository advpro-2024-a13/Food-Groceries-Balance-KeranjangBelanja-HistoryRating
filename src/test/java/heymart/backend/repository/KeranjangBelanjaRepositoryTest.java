package heymart.backend.repository;

import heymart.backend.models.KeranjangBelanja;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class KeranjangBelanjaRepositoryTest {

    @Mock
    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Test
    public void testFindByOwnerId() {
        String ownerId = "ownerId123";
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setOwnerId(ownerId);

        when(keranjangBelanjaRepository.findById(ownerId)).thenReturn(Optional.of(keranjangBelanja));

        Optional<KeranjangBelanja> foundKeranjangBelanja = keranjangBelanjaRepository.findById(ownerId);

        assertTrue(foundKeranjangBelanja.isPresent());
        assertEquals(keranjangBelanja, foundKeranjangBelanja.get());
    }
}