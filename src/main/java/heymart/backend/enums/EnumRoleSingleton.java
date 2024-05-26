package heymart.backend.enums;

import java.util.HashMap;

public enum EnumRoleSingleton {

    INSTANCE;

    private final HashMap<String, String> stringValues;

    EnumRoleSingleton() {
        stringValues = new HashMap<>();
        stringValues.put("Pembeli", "ROLE_PEMBELI");
        stringValues.put("Pengelola", "ROLE_PENGELOLA");
        stringValues.put("Admin", "ROLE_ADMIN");
    }

    public static EnumRoleSingleton getInstance() {
        return INSTANCE;
    }

    public void addStringValue(String key, String value) {
        stringValues.put(key, value);
    }

    public String getStringValue(String key) {
        return stringValues.get(key);
    }
}
