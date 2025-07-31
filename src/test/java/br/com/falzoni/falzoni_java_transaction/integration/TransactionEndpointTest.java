package br.com.falzoni.falzoni_java_transaction.integration;


import br.com.falzoni.falzoni_java_transaction.models.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionEndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void transaction_receive_returnsCreated() throws Exception {
        // arrange
        Transaction transaction = new Transaction(BigDecimal.valueOf(1000), OffsetDateTime.of(LocalDateTime.of(2025, 7, 30,  10, 11, 15), ZoneOffset.of("-03:00")));

        this.objectMapper.registerModule(new JavaTimeModule());
        String payload = this.objectMapper.writeValueAsString(transaction);

        // act
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/transacao")
                .content(payload).contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    void transaction_receive_returnsUnprocessableEntity() throws Exception {
        // arrange
        Transaction transaction = new Transaction(BigDecimal.valueOf(-1000), OffsetDateTime.of(LocalDateTime.of(2025, 7, 30,  10, 11, 15), ZoneOffset.of("-03:00")));

        this.objectMapper.registerModule(new JavaTimeModule());
        String payload = this.objectMapper.writeValueAsString(transaction);

        // act
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/transacao")
                        .content(payload).contentType(MediaType.APPLICATION_JSON))
                // assert
                .andExpect(status().isUnprocessableEntity()).andReturn();
    }

    @Test
    void transaction_receive_returnsBadRequest() throws Exception {
        // arrange
        String payload = this.objectMapper.writeValueAsString(null);

        // act
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/transacao")
                        .content(payload).contentType(MediaType.APPLICATION_JSON))
                // assert
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void transaction_delete_returnsOk() throws Exception {
        // No arrange
        // act
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/transacao"))
                // assert
                .andExpect(status().isOk()).andReturn();
    }
}
