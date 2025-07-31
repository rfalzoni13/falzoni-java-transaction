package br.com.falzoni.falzoni_java_transaction.unit;

import br.com.falzoni.falzoni_java_transaction.models.SummaryStatistics;
import br.com.falzoni.falzoni_java_transaction.models.Transaction;
import br.com.falzoni.falzoni_java_transaction.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    private TransactionService service;

    @Test
    public void transactionService_receive_success_test() {
        // Arrange
        Transaction transaction = new Transaction(BigDecimal.valueOf(1000),
                java.time.OffsetDateTime.now());

        // Act
        this.service.receive(transaction);

        // Assert
        assertTrue(this.service.getTransactions().contains(transaction));
        assertFalse(this.service.getTransactions().isEmpty());
    }

    @Test
    public void transactionService_receive_throws_nullable_test() {
        // Arrange, Act and Assert
        assertThrows(NullPointerException.class, () -> {
            this.service.receive(null);
        });
    }

    @Test
    public void transactionService_clear_test() {
        // Arrange and Act
        this.service.clear();

        // Assert
        assertTrue(this.service.getTransactions().isEmpty());
    }

    @Test
    public void transactionService_getStatistics_success_test() {
        // Arrange
        this.service.clear();

        Transaction transaction1 = new Transaction(BigDecimal.valueOf(1000),
                java.time.OffsetDateTime.now().minusSeconds(10));
        Transaction transaction2 = new Transaction(BigDecimal.valueOf(2000),
                java.time.OffsetDateTime.now().minusSeconds(20));
        Transaction transaction3 = new Transaction(BigDecimal.valueOf(1000),
                java.time.OffsetDateTime.now().minusSeconds(30));

        this.service.receive(transaction1);
        this.service.receive(transaction2);
        this.service.receive(transaction3);
        // Act
        SummaryStatistics statistics = this.service.getStatistics();

        // Assert
        assertEquals(3, statistics.count());
        assertEquals(4000, statistics.sum());
        assertEquals(1333.33, Math.round(statistics.average() * 100.0) / 100.0);
        assertEquals(1000, statistics.min());
        assertEquals(2000, statistics.max());
    }
}
