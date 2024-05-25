package heymart.backend.service;

import heymart.backend.models.KeranjangBelanja;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface KeranjangBelanjaService {
    KeranjangBelanja createNewKeranjangBelanja(Long ownerId);
    KeranjangBelanja addProductToKeranjangBelanja(Long ownerId, UUID productId, int quantity);
    KeranjangBelanja removeProductFromKeranjangBelanja(Long ownerId, UUID productId);
    void clearKeranjangBelanja(Long ownerId);
    boolean existsByOwnerId(Long ownerId);
    KeranjangBelanja getKeranjangBelanjaByOwnerId(Long ownerId);
}