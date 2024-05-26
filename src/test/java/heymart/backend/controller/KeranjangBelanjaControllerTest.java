package heymart.backend.controller;

import heymart.backend.dto.GetUpdateRequest;
import heymart.backend.models.KeranjangBelanja;
import heymart.backend.service.KeranjangBelanjaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KeranjangBelanjaControllerTest {
    @Mock
    private KeranjangBelanjaServiceImpl keranjangBelanjaService;

    @InjectMocks
    private KeranjangBelanjaController keranjangBelanjaController;

    private KeranjangBelanja keranjangBelanja;

    @BeforeEach
    void setUp(){
        Long ownerId = 1L;
        HashMap<UUID, Integer> products = new HashMap<>();
        products.put(UUID.randomUUID(), 5);

        keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setOwnerId(ownerId);
        keranjangBelanja.setProducts(products);
    }

    @Test
    void testGetKeranjangBelanjaByOwnerId(){
        Long ownerId = keranjangBelanja.getOwnerId();
        when(keranjangBelanjaService.existsByOwnerId(ownerId)).thenReturn(true);
        when(keranjangBelanjaService.getKeranjangBelanjaByOwnerId(ownerId)).thenReturn(keranjangBelanja);

        ResponseEntity<?> responseEntity = keranjangBelanjaController.getKeranjangBelanjaByOwnerId(ownerId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertTrue(responseEntity.getBody() instanceof KeranjangBelanja);

        KeranjangBelanja result = (KeranjangBelanja) responseEntity.getBody();

        assertEquals(keranjangBelanja.getOwnerId(), result.getOwnerId());
        assertEquals(keranjangBelanja.getProducts(), result.getProducts());
    }

    @Test
    void testCreateNewKeranjangBelanja(){
        Long ownerId = 1L;

        when(keranjangBelanjaService.existsByOwnerId(ownerId)).thenReturn(false);

        ResponseEntity<?> response = keranjangBelanjaController.createNewKeranjangBelanja(ownerId);
        assertEquals(ResponseEntity.ok(KeranjangBelanjaController.FREQUENTLY_USED_STRING + ownerId + " created."), response);
    }

    @Test
    void testUpdateKeranjangBelanja(){
        Long ownerId = 1L;
        HashMap<UUID, Integer> updatedProducts = new HashMap<>();
        updatedProducts.put(UUID.randomUUID(), 5);

        GetUpdateRequest updateRequest = new GetUpdateRequest();
        updateRequest.setOwnerId(ownerId);
        updateRequest.setUpdatedProducts(updatedProducts);

        when(keranjangBelanjaService.existsByOwnerId(ownerId)).thenReturn(true);

        ResponseEntity<?> response = keranjangBelanjaController.updateKeranjangBelanja(updateRequest);
        assertEquals(ResponseEntity.ok(KeranjangBelanjaController.FREQUENTLY_USED_STRING + ownerId + " is updated."), response);
    }

    @Test
    void testClearKeranjangBelanja(){
        Long ownerId = 1L;

        when(keranjangBelanjaService.existsByOwnerId(ownerId)).thenReturn(true);

        ResponseEntity<?> response = keranjangBelanjaController.clearKeranjangBelanja(ownerId);
        assertEquals(ResponseEntity.ok(KeranjangBelanjaController.FREQUENTLY_USED_STRING + ownerId + " is deleted."), response);
    }
}