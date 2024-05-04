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

import java.util.Optional;

public class BalanceServiceImplTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testModifyBalance() {
        Long ownerId = 123L;
        Long initialBalance = 1000L;
        Long amountToAdd = 500L;
        Balance balance = Balance.builder()
                .ownerId(ownerId)
                .balance(initialBalance)
                .build();

        when(balanceRepository.findById(ownerId)).thenReturn(Optional.of(balance));
        when(balanceRepository.save(any(Balance.class))).thenReturn(balance);

        Balance modifiedBalance = balanceService.modifyBalance(ownerId, amountToAdd);

        assertEquals(initialBalance + amountToAdd, modifiedBalance.getBalance());
    }

    @Test
    public void testGetBalanceById() {
        Long ownerId = 123L;
        Long expectedBalance = 1000L;

        when(balanceRepository.getReferenceById(ownerId).getBalance()).thenReturn(expectedBalance);

        Long actualBalance = balanceService.getBalanceById(ownerId);

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testAddNewBalance() {
        Long ownerId = 123L;
        Balance balance = Balance.builder()
                .ownerId(ownerId)
                .balance(0L)
                .build();

        when(balanceRepository.save(any(Balance.class))).thenReturn(balance);

        Balance createdBalance = balanceService.addNewBalance(ownerId);

        assertEquals(balance, createdBalance);
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