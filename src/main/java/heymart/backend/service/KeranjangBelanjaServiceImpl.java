package heymart.backend.service;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.repository.KeranjangBelanjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KeranjangBelanjaServiceImpl implements KeranjangBelanjaService {

    private final KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Autowired
    public KeranjangBelanjaServiceImpl(KeranjangBelanjaRepository keranjangBelanjaRepository){
        this.keranjangBelanjaRepository = keranjangBelanjaRepository;
    }

    @Override
    public KeranjangBelanja createNewKeranjangBelanja(Long ownerId){
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.builder()
                .ownerId(ownerId)
                .products(new HashMap<>())
                .build();
        return keranjangBelanjaRepository.save(keranjangBelanja);
    }

    @Override
    public KeranjangBelanja addProductToKeranjangBelanja(Long ownerId, UUID productId, int quantity){
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findById(ownerId).orElse(null);
        if (keranjangBelanja != null) {
            keranjangBelanja.getProducts().put(productId, quantity);
            return keranjangBelanjaRepository.save(keranjangBelanja);
        }
        return null;
    }

    @Override
    public KeranjangBelanja removeProductFromKeranjangBelanja(Long ownerId, UUID productId){
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findById(ownerId).orElse(null);
        if (keranjangBelanja != null) {
            keranjangBelanja.getProducts().remove(productId);
            return keranjangBelanjaRepository.save(keranjangBelanja);
        }
        return null;
    }

    @Override
    public void clearKeranjangBelanja(Long ownerId){
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findById(ownerId).orElse(null);
        if (keranjangBelanja != null) {
            keranjangBelanja.getProducts().clear();
            keranjangBelanjaRepository.save(keranjangBelanja);
        }
    }

    @Override
    public boolean existsByOwnerId(Long ownerId){
        return keranjangBelanjaRepository.existsById(ownerId);
    }

    @Override
    public CompletableFuture<KeranjangBelanja> getKeranjangBelanjaByOwnerId(Long ownerId){
        return CompletableFuture.supplyAsync(() -> keranjangBelanjaRepository.findById(ownerId).orElse(null));
    }
}