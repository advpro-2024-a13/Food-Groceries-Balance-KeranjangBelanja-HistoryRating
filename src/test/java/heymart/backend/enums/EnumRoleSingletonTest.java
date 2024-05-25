package heymart.backend.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnumRoleSingletonTest {

    @Test
    void testGetInstance() {
        EnumRoleSingleton instance = EnumRoleSingleton.getInstance();
        assertNotNull(instance);
    }

    @Test
    void testAddStringValue() {
        EnumRoleSingleton instance = EnumRoleSingleton.getInstance();
        instance.addStringValue("TestKey", "TestValue");
        String value = instance.getStringValue("TestKey");
        assertEquals("TestValue", value);
    }

    @Test
    void testGetStringValue() {
        EnumRoleSingleton instance = EnumRoleSingleton.getInstance();
        String value = instance.getStringValue("Pembeli");
        assertEquals("ROLE_PEMBELI", value);
    }
}

