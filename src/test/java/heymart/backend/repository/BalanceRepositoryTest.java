package heymart.backend.repository;

import heymart.backend.models.Balance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceRepositoryTest {

    @Mock
    private BalanceRepository balanceRepository;

    @Test
    void testFindByOwnerId() {
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
    void testGetBalanceByOwnerId() {
        when(balanceRepository.findById(123L)).thenReturn(Optional.of(Balance.builder()
                .ownerId(123L)
                .balance(1000L)
                .build()));

        Optional<Balance> foundBalance = balanceRepository.findById(123L);

        assertTrue(foundBalance.isPresent());
        assertEquals(1000L, foundBalance.get().getBalance());
    }
}