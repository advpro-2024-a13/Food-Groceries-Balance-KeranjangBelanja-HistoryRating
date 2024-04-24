package heymart.backend;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = BalanceKeranjangBelanjaHistoryRatingApplication.class)
class BalanceKeranjangBelanjaHistoryRatingApplicationTests {

    @Test
    @PostConstruct
    void contextLoads() {
    }

}