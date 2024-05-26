package heymart.backend.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class EnumRoleSingletonTest {

    @Test
    public void testGetInstance() {
        EnumRoleSingleton instance = EnumRoleSingleton.getInstance();
        assertNotNull(instance);
    }

    @Test
    public void testAddStringValue() {
        EnumRoleSingleton instance = EnumRoleSingleton.getInstance();
        instance.addStringValue("TestKey", "TestValue");
        String value = instance.getStringValue("TestKey");
        assertEquals("TestValue", value);
    }

    @Test
    public void testGetStringValue() {
        EnumRoleSingleton instance = EnumRoleSingleton.getInstance();
        String value = instance.getStringValue("Pembeli");
        assertEquals("ROLE_PEMBELI", value);
    }
}

