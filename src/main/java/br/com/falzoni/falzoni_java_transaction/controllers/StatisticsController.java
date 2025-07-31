package br.com.falzoni.falzoni_java_transaction.controllers;

import br.com.falzoni.falzoni_java_transaction.models.SummaryStatistics;
import br.com.falzoni.falzoni_java_transaction.models.Transaction;
import br.com.falzoni.falzoni_java_transaction.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;
import java.util.HashSet;

@RestController
@RequestMapping("api/estatistica")
@Tag(name = "Estatística")
public class StatisticsController {
    Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private TransactionService service;

    @Operation(summary = "Calcular Estatísticas", description = "Endpoint que retorna as estatísticas das transações dos últimos 60 segundos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content)
    })
    @GetMapping
    public ResponseEntity<SummaryStatistics> getStatistics() {
        this.logger.info("Iniciando requisição das estatísticas");
        SummaryStatistics statistics = this.service.getStatistics();
        this.logger.info("Estatísticas calculadas com sucesso");
        return ResponseEntity.ok(statistics);
    }
}
