package heymart.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.UUID;

@Data
@Entity
@Table(name = "keranjangbelanja")
@Getter
public class KeranjangBelanja{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long keranjangBelanjaId;

    @Setter @ElementCollection
    private HashMap<UUID, Integer> productMap;

    public static KeranjangBelanjaBuilder getBuilder(){
        return new KeranjangBelanjaBuilder();
    }
}