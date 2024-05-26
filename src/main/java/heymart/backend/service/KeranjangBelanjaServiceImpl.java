package heymart.backend.service;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.repository.KeranjangBelanjaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class KeranjangBelanjaServiceImpl implements KeranjangBelanjaService {

    private final KeranjangBelanjaRepository keranjangBelanjaRepository;

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
    public KeranjangBelanja updateKeranjangBelanja(Long ownerId, Map<UUID, Integer> updatedProducts){
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findById(ownerId).orElse(null);
        if (keranjangBelanja != null) {
            Map<UUID, Integer> currentProducts = keranjangBelanja.getProducts();
            for (Map.Entry<UUID, Integer> entry : updatedProducts.entrySet()) {
                UUID productId = entry.getKey();
                int quantity = entry.getValue();
                if (currentProducts.containsKey(productId)) {
                    currentProducts.put(productId, quantity);
                }
            }
            return keranjangBelanjaRepository.save(keranjangBelanja);
        }
        return null;
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
    public KeranjangBelanja getKeranjangBelanjaByOwnerId(Long ownerId){
        return keranjangBelanjaRepository.findById(ownerId).orElse(null);
    }
}