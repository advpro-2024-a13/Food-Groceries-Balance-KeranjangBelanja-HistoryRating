package heymart.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BalanceKeranjangBelanjaHistoryRatingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BalanceKeranjangBelanjaHistoryRatingApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMain() {
        BalanceKeranjangBelanjaHistoryRatingApplication.main(new String[] {});
    }

}
