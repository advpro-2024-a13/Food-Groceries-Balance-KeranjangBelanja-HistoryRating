import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import heymart.backend.models.Balance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BalanceRepositoryTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    @BeforeEach
    public void setUp() {
        Balance balance = new Balance(123L, 1000L);
        when(balanceRepository.findByOwnerId(123L)).thenReturn(balance);
    }

    @Test
    public void testFindByOwnerId() {
        Balance foundBalance = balanceService.findByOwnerId(123L);
        
        verify(balanceRepository).findByOwnerId(123L);
        
        assertEquals(123L, foundBalance.getOwnerId());
        assertEquals(1000L, foundBalance.getBalance());
    }
}
