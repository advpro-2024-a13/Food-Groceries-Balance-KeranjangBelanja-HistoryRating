package heymart.backend.service;

import org.springframework.stereotype.Service;

import heymart.backend.models.Balance;
import heymart.backend.repository.BalanceRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance modifyBalance(Long ownerId, Long amount) {
        Optional<Balance> balance = balanceRepository.findById(ownerId);
        if (balance.isPresent()) {
            balance.get().setBalance(amount);
            return balanceRepository.save(balance.get());
        }
        return null;
    }

    @Override
    public Long getBalanceById(Long ownerId) {
        if (balanceRepository.findById(ownerId).isPresent()) {
            return balanceRepository.findById(ownerId).get().getBalance();
        } else {
            return null;
        }
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

    @Override
    public CompletableFuture<Iterable<Balance>> getAllBalance() {
        return CompletableFuture.supplyAsync(balanceRepository::findAll);
    }
}
