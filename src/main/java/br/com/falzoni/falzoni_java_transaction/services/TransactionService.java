package br.com.falzoni.falzoni_java_transaction.services;

import br.com.falzoni.falzoni_java_transaction.models.SummaryStatistics;
import br.com.falzoni.falzoni_java_transaction.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final Collection<Transaction> transactions;

    @Autowired
    public TransactionService(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return this.transactions.stream().toList();
    }

    public void clear() {
        this.logger.info("Limpando transações registradas");
        this.transactions.clear();
    }

    public void receive(Transaction transaction) {
        if (transaction == null) throw new NullPointerException("Objeto nulo ou inválido");

        this.transactions.add(transaction);
    }

    public SummaryStatistics getStatistics() {
        this.logger.info("Calculando estatísticas das transações registradas nos últimos 60 segundos");

        StopWatch watch = new StopWatch();
        watch.start();

        HashSet<Transaction> validTransactions = this.transactions.stream()
                .filter(x -> x.getDataHora()
                        .isAfter(OffsetDateTime.now().minusSeconds(60)) &&
                        x.getDataHora().isBefore(OffsetDateTime.now()))
                .collect(Collectors.toCollection(HashSet::new));

        DoubleSummaryStatistics doubleSummaryStatistics = validTransactions.stream()
                .mapToDouble(x -> x.getValor().doubleValue())
                .summaryStatistics();

        SummaryStatistics statistics = new SummaryStatistics(doubleSummaryStatistics.getCount(),
                doubleSummaryStatistics.getAverage(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getMax(),
                doubleSummaryStatistics.getSum());

        watch.stop();

        this.logger.info("Estatísticas calculadas em: {}ms", watch.getTotalTimeMillis());

        return statistics;
    }
}
