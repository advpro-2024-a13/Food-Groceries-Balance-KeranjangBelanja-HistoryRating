package heymart.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heymart.backend.models.Balance;
import heymart.backend.repository.BalanceRepository;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public Balance modifyBalance(Long ownerId, Long amount) {
        Balance balance = balanceRepository.findByOwnerId(ownerId);
        balance.setBalance(balance.getBalance() + amount);
        return balanceRepository.save(balance);
    }

    @Override
    public Balance getBalanceById(Long ownerId) {
        return balanceRepository.findByOwnerId(ownerId);
    }

    @Override
    public Balance addNewBalance(Long ownerId) {
        Balance balance = new Balance(ownerId, 0L);
        return balanceRepository.save(balance);
    }

    @Override
    public void deleteBalance(Long ownerId) {
        balanceRepository.deleteById(ownerId);
    }

    @Override
    public boolean existsById(Long ownerId) {
        return balanceRepository.existsById(ownerId);
    }
}
