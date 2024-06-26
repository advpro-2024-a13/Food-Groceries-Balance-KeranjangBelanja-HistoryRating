package heymart.backend.service;

import heymart.backend.models.Balance;
import heymart.backend.repository.BalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BalanceServiceImplTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testModifyBalance() {
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

        assertEquals(amountToAdd, modifiedBalance.getBalance());
    }

    @Test
    void testGetBalanceById() {
        Long ownerId = 123L;
        Long expectedBalance = 1000L;

        when (balanceRepository.findById(ownerId)).thenReturn(Optional.of(Balance.builder()
                .ownerId(ownerId)
                .balance(expectedBalance)
                .build()));

        Long actualBalance = balanceService.getBalanceById(ownerId);

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void testAddNewBalance() {
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
    void testDeleteBalance() {
        Long ownerId = 123L;

        balanceService.deleteBalance(ownerId);

        verify(balanceRepository).deleteById(ownerId);
    }

    @Test
    void testExistsById() {
        Long ownerId = 123L;

        when(balanceRepository.existsById(ownerId)).thenReturn(true);

        boolean exists = balanceService.existsById(ownerId);

        assertTrue(exists);
    }

    @Test
    void testGetAllBalance() {
        Balance balance1 = Balance.builder()
                .ownerId(123L)
                .balance(1000L)
                .build();
        Balance balance2 = Balance.builder()
                .ownerId(456L)
                .balance(2000L)
                .build();

        when(balanceRepository.findAll()).thenReturn(java.util.List.of(balance1, balance2));

        Iterable<Balance> allBalance = balanceService.getAllBalance().join();

        assertEquals(java.util.List.of(balance1, balance2), allBalance);
    }

    @Test
    void testModifyBalanceWithNonExistingBalance() {
        Long ownerId = 123L;
        Long amountToAdd = 500L;

        when(balanceRepository.findById(ownerId)).thenReturn(Optional.empty());

        assertNull(balanceService.modifyBalance(ownerId, amountToAdd));
    }

    @Test
    void testGetBalanceByIdWithNonExistingBalance() {
        Long ownerId = 123L;

        when(balanceRepository.findById(ownerId)).thenReturn(Optional.empty());

        assertNull(balanceService.getBalanceById(ownerId));
    }
}