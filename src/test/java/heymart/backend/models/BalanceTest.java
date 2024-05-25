package heymart.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BalanceTest {

    private Balance balance;

    @BeforeEach
    public void setUp() {
        balance = Balance.builder()
                .build();
    }

    @Test
    void testGetSetOwnerId() {
        Long ownerId = 123L;
        balance.setOwnerId(ownerId);
        assertEquals(ownerId, balance.getOwnerId());
    }

    @Test
    void testGetSetBalance() {
        Long balanceAmount = 1000L;
        balance.setBalance(balanceAmount);
        assertEquals(balanceAmount, balance.getBalance());
    }

    @Test
    void testConstructor() {
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
    void testToString() {
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
    void testBalanceBuilderToString() {
        Long ownerId = 123L;
        Long balanceAmount = 1000L;
        Balance.BalanceBuilder builder = Balance.builder().ownerId(ownerId).balance(balanceAmount);

        String expected = "Balance.BalanceBuilder(ownerId=" + ownerId + ", balance=" + balanceAmount + ")";
        assertEquals(expected, builder.toString());
    }
}
