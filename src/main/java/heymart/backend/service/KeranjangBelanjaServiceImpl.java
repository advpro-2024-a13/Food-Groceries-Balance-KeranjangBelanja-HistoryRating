package heymart.backend.service;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.models.KeranjangBelanjaBuilder;

import heymart.backend.repository.KeranjangBelanjaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class KeranjangBelanjaServiceImpl implements KeranjangBelanjaService {

    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Override
    public KeranjangBelanja createKeranjangBelanja(Long userId) {
        KeranjangBelanja keranjangBelanja = new KeranjangBelanjaBuilder()
                .setKeranjangBelanjaId(userId)
                .setProductMap(new HashMap<UUID, Integer>())
                .build();
        return keranjangBelanja;
    }

    @Override
    public KeranjangBelanja findKeranjangBelanjaById(Long userId) {
        return keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
    }

    @Override
    public KeranjangBelanja addProductToKeranjang(Long userId, UUID productId) {
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
        HashMap<UUID, Integer> productMap = keranjangBelanja.getProductMap();

        if (productMap.containsKey(productId)) {
            productMap.put(productId, productMap.get(productId) + 1);
        } else {
            productMap.put(productId, 1);
        }
        return keranjangBelanja;
    }

    @Override
    public Integer countTotalProductInKeranjang(HashMap<UUID, Integer> productMap) {
        return productMap.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public Long countTotalProductPriceInKeranjang(HashMap<UUID, Integer> productMap) {
        // TODO: Create calculating total product price for products in keranjang belanja
        return null;
    }

    @Override
    public boolean checkout() {
        // TODO: Create checkout mechanism
        return false;
    }

    @Async
    @Override
    public void clearKeranjangBelanja(Long userId) {
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
        keranjangBelanja.getProductMap().clear();
        keranjangBelanjaRepository.save(keranjangBelanja);
    }
}