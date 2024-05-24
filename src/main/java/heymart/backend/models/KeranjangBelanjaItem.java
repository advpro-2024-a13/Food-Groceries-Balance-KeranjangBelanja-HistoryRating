package heymart.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeranjangBelanjaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "keranjang_belanja_id")
    private KeranjangBelanja keranjangBelanja;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public static KeranjangBelanjaItemBuilder getBuilder(){
        return new KeranjangBelanjaItemBuilder();
    }
}