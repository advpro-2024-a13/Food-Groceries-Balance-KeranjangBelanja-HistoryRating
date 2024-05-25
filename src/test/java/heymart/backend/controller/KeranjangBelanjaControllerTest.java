package heymart.backend.controller;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.service.KeranjangBelanjaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KeranjangBelanjaControllerTest {

    @Mock
    private KeranjangBelanjaService keranjangBelanjaService;

    @InjectMocks
    private KeranjangBelanjaController keranjangBelanjaController;

    @BeforeEach
    void setUp() {
        keranjangBelanjaController = new KeranjangBelanjaController(keranjangBelanjaService);
    }

    @Test
    void testCreateKeranjangBelanjaPage() {
        Model model = mock(Model.class);
        String result = keranjangBelanjaController.createKeranjangBelanjaPage(model);
        assertEquals("CreateKeranjangBelanja", result);
    }

    @Test
    void testCreateKeranjangBelanjaPost() {
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        when(keranjangBelanjaService.createKeranjangBelanja(keranjangBelanja)).thenReturn(keranjangBelanja);
        String result = keranjangBelanjaController.createKeranjangBelanjaPost(keranjangBelanja);
        assertEquals("redirect::list", result);
        verify(keranjangBelanjaService, times(1)).createKeranjangBelanja(keranjangBelanja);
    }
}