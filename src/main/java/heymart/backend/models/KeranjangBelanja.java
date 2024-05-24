package heymart.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeranjangBelanja{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long ownerId;

    @OneToMany(mappedBy = "keranjangbelanja", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Product> cartItems = new ArrayList<>();

    public static KeranjangBelanjaBuilder getBuilder(){
        return new KeranjangBelanjaBuilder();
    }
}