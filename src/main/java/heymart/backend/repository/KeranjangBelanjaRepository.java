package heymart.backend.repository;

import heymart.backend.model.KeranjangBelanja;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KeranjangBelanjaRepository {
    private List<KeranjangBelanja> keranjangBelanjaList = new ArrayList<>();
    public KeranjangBelanja save(KeranjangBelanja keranjangBelanja) {
        int i = 0;
        for (KeranjangBelanja savedKeranjangBelanja : keranjangBelanjaList){
            if(savedKeranjangBelanja.getOwnerId().equals(keranjangBelanja.getOwnerId())){
                keranjangBelanjaList.remove(i);
                keranjangBelanjaList.add(i, keranjangBelanja);
                return keranjangBelanja;
            }
            i += 1;
        }

        keranjangBelanjaList.add(keranjangBelanja);
        return keranjangBelanja;
    }

    public KeranjangBelanja findByOwnerId(String ownerId) {
        for (KeranjangBelanja savedKeranjangBelanja : keranjangBelanjaList){
            if(savedKeranjangBelanja.getOwnerId().equals(ownerId)){
                return savedKeranjangBelanja;
            }
        }
        return null;
    }
}