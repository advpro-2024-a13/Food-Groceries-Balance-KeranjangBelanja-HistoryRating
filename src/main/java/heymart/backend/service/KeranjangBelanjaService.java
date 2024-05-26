package heymart.backend.service;

import heymart.backend.models.KeranjangBelanja;

import java.util.Map;
import java.util.UUID;

public interface KeranjangBelanjaService {
    KeranjangBelanja createNewKeranjangBelanja(Long ownerId);
    KeranjangBelanja updateKeranjangBelanja(Long ownerId, Map<UUID, Integer> updatedProducts);
    KeranjangBelanja addProductToKeranjangBelanja(Long ownerId, UUID productId, int quantity);
    KeranjangBelanja removeProductFromKeranjangBelanja(Long ownerId, UUID productId);
    void clearKeranjangBelanja(Long ownerId);
    boolean existsByOwnerId(Long ownerId);
    KeranjangBelanja getKeranjangBelanjaByOwnerId(Long ownerId);
}