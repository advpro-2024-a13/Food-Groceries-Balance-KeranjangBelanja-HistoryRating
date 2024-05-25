package heymart.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "keranjangbelanja",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "ownerId")
        })
public class KeranjangBelanja{

    @Id
    private Long ownerId;

    @ElementCollection
    @CollectionTable(name = "products", joinColumns = @JoinColumn(name = "ownerId"))
    @MapKeyColumn(name = "productId")
    @Column(name = "quantity")
    private Map<UUID, Integer> products;
}