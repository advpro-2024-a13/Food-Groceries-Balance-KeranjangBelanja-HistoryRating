package heymart.backend.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@Entity
public class KeranjangBelanja {

    @Id
    String ownerId;

    @OneToMany(mappedBy = "keranjangBelanja")
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