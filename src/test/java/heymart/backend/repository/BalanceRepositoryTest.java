package heymart.backend.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import heymart.backend.models.Balance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BalanceRepositoryTest {

    @Mock
    private BalanceRepository balanceRepository;

    @Test
    public void testFindByOwnerId() {
        Balance balance = Balance.builder()
                .ownerId(123L)
                .balance(1000L)
                .build();

        when(balanceRepository.findById(123L)).thenReturn(Optional.of(balance));

        Optional<Balance> foundBalance = balanceRepository.findById(123L);

        assertTrue(foundBalance.isPresent());
        assertEquals(123L, foundBalance.get().getOwnerId());
        assertEquals(1000L, foundBalance.get().getBalance());
    }

    @Test
    public void testGetBalanceByOwnerId() {
        when(balanceRepository.findById(123L)).thenReturn(Optional.of(Balance.builder()
                .ownerId(123L)
                .balance(1000L)
                .build()));

        Optional<Balance> foundBalance = balanceRepository.findById(123L);

        assertTrue(foundBalance.isPresent());
        assertEquals(1000L, foundBalance.get().getBalance());
    }
}