package heymart.backend.service;

import heymart.backend.models.Balance;

public interface BalanceService {
    Balance modifyBalance(Long ownerId, Long amount);
    Long getBalanceById(Long ownerId);
    Balance addNewBalance(Long ownerId);
    void deleteBalance(Long ownerId);
    boolean existsById(Long ownerId);
    Iterable<Balance> getAllBalance();
}
