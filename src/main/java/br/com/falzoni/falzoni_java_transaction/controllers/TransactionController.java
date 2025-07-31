package br.com.falzoni.falzoni_java_transaction.controllers;

import br.com.falzoni.falzoni_java_transaction.models.Transaction;
import br.com.falzoni.falzoni_java_transaction.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;
import java.util.HashSet;

@RestController
@RequestMapping("api/transacao")
@Tag(name = "Transação")
public class TransactionController {
    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService service;

    @Operation(summary = "Registrar transação", description = "Endpoint que grava uma nova transação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Object> receive(@Valid @RequestBody Transaction transaction) {
        try {
            this.logger.info("Iniciando requisição de cadastro!");
            this.service.receive(transaction);
            this.logger.info("Cadastro efetuado com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NullPointerException ex) {
            this.logger.warn(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Remover transações", description = "Endpoint que remove todas as transações registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoubleSummaryStatistics.class)))
    })
    @DeleteMapping
    public ResponseEntity<HashSet<Transaction>> delete() {
        this.logger.info("Iniciando requisição de limpeza de transações");
        this.service.clear();
        this.logger.info("Transações removidas");
        return ResponseEntity.ok().build();
    }
}
