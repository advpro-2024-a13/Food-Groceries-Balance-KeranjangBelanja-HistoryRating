import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import heymart.backend.models.Balance;

public class BalanceTest {

    private Balance balance;

    @BeforeEach
    public void setUp() {
        balance = new Balance();
    }

    @Test
    public void testGetSetOwnerId() {
        Long ownerId = 123L;
        balance.setOwnerId(ownerId);
        assertEquals(ownerId, balance.getOwnerId());
    }

    @Test
    public void testGetSetBalance() {
        Long balanceAmount = 1000L;
        balance.setBalance(balanceAmount);
        assertEquals(balanceAmount, balance.getBalance());
    }

    @Test
    public void testConstructor() {
        Long ownerId = 456L;
        Long balanceAmount = 2000L;
        Balance balance = new Balance(ownerId, balanceAmount);
        assertEquals(ownerId, balance.getOwnerId());
        assertEquals(balanceAmount, balance.getBalance());
    }
}
