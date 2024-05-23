package heymart.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "KeranjangBelanja(ownerId=" + ownerId + ", products=" + products.toString() + ")";
    }
}