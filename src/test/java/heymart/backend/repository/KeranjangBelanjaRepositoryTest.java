package heymart.backend.repository;

import heymart.backend.models.KeranjangBelanja;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KeranjangBelanjaRepositoryTest {

    @Mock
    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Test
    void testFindByOwnerId() {
        String ownerId = "ownerId123";
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setOwnerId(ownerId);

        when(keranjangBelanjaRepository.findById(ownerId)).thenReturn(Optional.of(keranjangBelanja));

        Optional<KeranjangBelanja> foundKeranjangBelanja = keranjangBelanjaRepository.findById(ownerId);

        assertTrue(foundKeranjangBelanja.isPresent());
        assertEquals(keranjangBelanja, foundKeranjangBelanja.get());
    }
}