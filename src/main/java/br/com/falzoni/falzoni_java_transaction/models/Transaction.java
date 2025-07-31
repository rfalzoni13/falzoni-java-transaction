package br.com.falzoni.falzoni_java_transaction.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transaction {
    @NotNull
    @Min(0)
    private BigDecimal valor;

    @NotNull
    private OffsetDateTime dataHora;

    public Transaction(BigDecimal valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Transaction() {}

    public BigDecimal getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
