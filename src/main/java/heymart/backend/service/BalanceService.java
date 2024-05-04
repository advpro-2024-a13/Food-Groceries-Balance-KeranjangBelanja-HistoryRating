package heymart.backend.service;

import heymart.backend.models.Balance;

public interface BalanceService {
    public Balance modifyBalance(Long ownerId, Long amount);
    public Long getBalanceById(Long ownerId);
    public Balance addNewBalance(Long ownerId);
    public void deleteBalance(Long ownerId);
    public boolean existsById(Long ownerId);
}
