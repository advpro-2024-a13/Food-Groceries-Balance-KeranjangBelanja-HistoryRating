package heymart.backend.controller;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.service.KeranjangBelanjaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class KeranjangBelanjaControllerTest {

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
        String result = keranjangBelanjaController.createKeranjangBelanjaPost(keranjangBelanja);
        assertEquals("redirect::list", result);
    }
}