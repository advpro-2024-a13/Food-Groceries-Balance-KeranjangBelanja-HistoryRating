package heymart.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import heymart.backend.models.Balance;
import heymart.backend.repository.BalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BalanceServiceImplTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testModifyBalance() {
        Long ownerId = 123L;
        Long initialBalance = 1000L;
        Long amountToAdd = 500L;
        Balance balance = new Balance(ownerId, initialBalance);

        when(balanceRepository.findByOwnerId(ownerId)).thenReturn(balance);
        when(balanceRepository.save(any(Balance.class))).thenReturn(balance);

        Balance modifiedBalance = balanceService.modifyBalance(ownerId, amountToAdd);

        assertEquals(initialBalance + amountToAdd, modifiedBalance.getBalance());
    }

    @Test
    public void testGetBalanceById() {
        Long ownerId = 123L;
        Balance balance = new Balance(ownerId, 1000L);

        when(balanceRepository.findByOwnerId(ownerId)).thenReturn(balance);

        Balance retrievedBalance = balanceService.getBalanceById(ownerId);

        assertEquals(balance, retrievedBalance);
    }

    @Test
    public void testDeleteBalance() {
        Long ownerId = 123L;

        balanceService.deleteBalance(ownerId);

        verify(balanceRepository).deleteById(ownerId);
    }

    @Test
    public void testExistsById() {
        Long ownerId = 123L;

        when(balanceRepository.existsById(ownerId)).thenReturn(true);

        boolean exists = balanceService.existsById(ownerId);

        assertTrue(exists);
    }
}
