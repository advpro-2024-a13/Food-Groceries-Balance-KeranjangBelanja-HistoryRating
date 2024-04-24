package heymart.backend.controller;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.service.KeranjangBelanjaServiceImpl;
import org.mockito.*;
import org.springframework.ui.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class KeranjangBelanjaControllerTest {
    @Mock
    private KeranjangBelanjaServiceImpl keranjangBelanjaService;
    @Mock
    private Model model;
    @InjectMocks
    private KeranjangBelanjaController keranjangBelanjaController;

    @BeforeEach
    void setKeranjangBelanja(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateKeranjangBelanjaPage(){
        Model model = mock(Model.class);
        String result = keranjangBelanjaController.createKeranjangBelanjaPage(model);
        assertEquals("CreateKeranjangBelanja", result);
    }

    @Test
    void testCreateKeranjangBelanjaPost(){
        Model model = mock(Model.class);
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        Mockito.when(keranjangBelanjaService.createKeranjangBelanja(keranjangBelanja)).thenReturn(keranjangBelanja);
        String result = keranjangBelanjaController.createKeranjangBelanjaPost(keranjangBelanja, model);
        assertEquals("redirect::list", result);
    }
}