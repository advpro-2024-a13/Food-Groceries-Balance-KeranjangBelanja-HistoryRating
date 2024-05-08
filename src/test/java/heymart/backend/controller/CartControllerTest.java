package heymart.backend.controller;

import heymart.backend.models.Cart;
import heymart.backend.service.KeranjangBelanjaServiceImpl;
import org.mockito.*;
import org.springframework.ui.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
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
        Cart cart = new Cart();
        Mockito.when(keranjangBelanjaService.createKeranjangBelanja(cart)).thenReturn(cart);
        String result = keranjangBelanjaController.createKeranjangBelanjaPost(cart, model);
        assertEquals("redirect::list", result);
    }
}