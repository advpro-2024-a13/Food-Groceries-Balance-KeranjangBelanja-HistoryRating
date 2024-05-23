package heymart.backend.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BalanceTest {

    private Balance balance;

    @BeforeEach
    public void setUp() {
        balance = Balance.builder()
                .build();
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
        Balance balance = Balance.builder()
                .ownerId(ownerId)
                .balance(balanceAmount)
                .build();
        assertEquals(ownerId, balance.getOwnerId());
        assertEquals(balanceAmount, balance.getBalance());
    }

    @Test
    public void testToString() {
        Long ownerId = 789L;
        Long balanceAmount = 3000L;
        Balance balance = Balance.builder()
                .ownerId(ownerId)
                .balance(balanceAmount)
                .build();
        String expected = "Balance(ownerId=" + ownerId + ", balance=" + balanceAmount + ")";
        assertEquals(expected, balance.toString());
    }

    @Test
    public void testBalanceBuilderToString() {
        Long ownerId = 123L;
        Long balanceAmount = 1000L;
        Balance.BalanceBuilder builder = Balance.builder().ownerId(ownerId).balance(balanceAmount);

        String expected = "Balance.BalanceBuilder(ownerId=" + ownerId + ", balance=" + balanceAmount + ")";
        assertEquals(expected, builder.toString());
    }
}
