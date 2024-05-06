package heymart.backend.service;

import heymart.backend.models.Balance;

import java.util.concurrent.CompletableFuture;

public interface BalanceService {
    Balance modifyBalance(Long ownerId, Long amount);
    Long getBalanceById(Long ownerId);
    Balance addNewBalance(Long ownerId);
    void deleteBalance(Long ownerId);
    boolean existsById(Long ownerId);
    CompletableFuture<Iterable<Balance>> getAllBalance();
}
