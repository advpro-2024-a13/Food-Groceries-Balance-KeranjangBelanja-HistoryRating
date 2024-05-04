package heymart.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heymart.backend.models.Balance;
import heymart.backend.repository.BalanceRepository;

import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public Balance modifyBalance(Long ownerId, Long amount) {
        Optional<Balance> balance = balanceRepository.findById(ownerId);
        if (!balance.isEmpty()) {
            balance.get().setBalance(amount);
            return balanceRepository.save(balance.get());
        }
        return null;
    }

    @Override
    public Long getBalanceById(Long ownerId) {
        return balanceRepository.getReferenceById(ownerId).getBalance();
    }

    @Override
    public Balance addNewBalance(Long ownerId) {
        Balance balance = Balance.builder()
                .ownerId(ownerId)
                .balance(0L)
                .build();
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
