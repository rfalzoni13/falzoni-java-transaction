package br.com.falzoni.falzoni_java_transaction.integration;

import br.com.falzoni.falzoni_java_transaction.models.SummaryStatistics;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticEndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void statistic_getStatistics_returnsOk() throws Exception {
        // No arrange
        // act
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/estatistica"))
                // assert
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        SummaryStatistics responseBody = this.objectMapper.readValue(jsonResponse, SummaryStatistics.class);

        assertEquals(SummaryStatistics.class, responseBody.getClass());
    }
}
