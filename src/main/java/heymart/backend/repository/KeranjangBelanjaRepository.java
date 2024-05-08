package heymart.backend.repository;

import heymart.backend.models.Cart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KeranjangBelanjaRepository {
    private List<Cart> cartList = new ArrayList<>();
    public Cart save(Cart cart) {
        int i = 0;
        for (Cart savedCart : cartList){
            if(savedCart.getOwnerId().equals(cart.getOwnerId())){
                cartList.remove(i);
                cartList.add(i, cart);
                return cart;
            }
            i += 1;
        }

        cartList.add(cart);
        return cart;
    }

    public Cart findByOwnerId(String ownerId) {
        for (Cart savedCart : cartList){
            if(savedCart.getOwnerId().equals(ownerId)){
                return savedCart;
            }
        }
        return null;
    }
}