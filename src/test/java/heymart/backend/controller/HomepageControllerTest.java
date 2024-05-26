package heymart.backend.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HomepageControllerTest {
    @Test
    void homepage() {
        HomepageController homepageController = new HomepageController();
        Assertions.assertEquals("homepage", homepageController.homepage());
    }
}