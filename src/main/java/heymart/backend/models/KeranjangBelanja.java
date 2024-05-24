package heymart.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "KeranjangBelanja")
public class KeranjangBelanja{

    @Id
    @Column(name = "ownerId")
    private Long ownerId;

    @ElementCollection
    @CollectionTable(name = "products", joinColumns = @JoinColumn(name = "ownerId"))
    @MapKeyColumn(name = "productId")
    @Column(name = "quantity")
    private Map<Long, Integer> products;

    @Column(name = "totalPrice")
    private long totalPrice;

    public KeranjangBelanja(Long ownerId){
        this.ownerId = ownerId;
    }

    public KeranjangBelanja(){}
}