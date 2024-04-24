package heymart.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HomePageControllerTest {

    @BeforeEach
    void setUp(){
    }

    @InjectMocks
    HomepageController homepageController;

    @Test
    void homePageReturnString(){
        String result = homepageController.homePage();
        assertEquals("HomePage", result);
    }
}