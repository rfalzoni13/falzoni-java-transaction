package br.com.falzoni.falzoni_java_transaction.models;

public record SummaryStatistics(long count, double average, double min, double max, double sum) {
    public SummaryStatistics(long count, double average, double min, double max, double sum) {
        this.min = Double.isInfinite(min) ? 0 : min;
        this.max = Double.isInfinite(max) ? 0 : max;
        this.average = average;
        this.sum = sum;
        this.count = count;
    }
}
