package heymart.backend.enums;

import lombok.Getter;

@Getter
public enum CheckoutStatus {
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    private final String value;

    private CheckoutStatus(String value){
        this.value = value;
    }

    public static boolean contains(String param){
        for(CheckoutStatus checkoutStatus : CheckoutStatus.values()){
            if(checkoutStatus.name().equals(param)){
                return true;
            }
        }
        return false;
    }
}