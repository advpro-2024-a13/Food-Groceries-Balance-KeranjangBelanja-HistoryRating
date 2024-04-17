package heymart.backend.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class KeranjangBelanja {
    String ownerId;
    List<Product> products;

    public KeranjangBelanja(String ownerId, List<Product> products){
        this.ownerId = ownerId;

        if(products.isEmpty()){
            throw new IllegalArgumentException();
        } else{
            this.products = products;
        }
    }

    public KeranjangBelanja(){

    }
}