package heymart.backend.service;

import heymart.backend.models.KeranjangBelanja;


import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface KeranjangBelanjaService {
    KeranjangBelanja createKeranjangBelanja(Long userId);
    KeranjangBelanja findKeranjangBelanjaById(Long userId);
    KeranjangBelanja addProductToKeranjang(Long userId, UUID productId);
    Integer countTotalProductInKeranjang(HashMap<UUID, Integer> productMap);
    Long countTotalProductPriceInKeranjang(HashMap<UUID, Integer> productMap);
    boolean checkout();
    void clearKeranjangBelanja(Long userId);
}