package heymart.backend.models;

import java.util.HashMap;
import java.util.UUID;

public class KeranjangBelanjaBuilder {
    private Long keranjangBelanjaId;
    private HashMap<UUID, Integer> productMap;

    public KeranjangBelanjaBuilder setKeranjangBelanjaId(Long keranjangBelanjaId){
        this.keranjangBelanjaId = keranjangBelanjaId;
        return this;
    }

    public KeranjangBelanjaBuilder setProductMap(HashMap<UUID, Integer> productMap){
        this.productMap = productMap;
        return this;
    }

    public KeranjangBelanja build(){
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setKeranjangBelanjaId(this.keranjangBelanjaId);
        keranjangBelanja.setProductMap(this.productMap);
        return keranjangBelanja;
    }
}