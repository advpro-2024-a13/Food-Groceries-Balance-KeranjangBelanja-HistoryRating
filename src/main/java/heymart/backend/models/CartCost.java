package heymart.backend.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartCost {
    private List<CartDto> cartItems;
    private double totalCost;

    public CartCost(List<CartDto> cartDtoList, double totalCost) {
        this.cartItems = cartDtoList;
        this.totalCost = totalCost;
    }
}