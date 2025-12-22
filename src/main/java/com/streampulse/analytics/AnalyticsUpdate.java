package com.streampulse.analytics;

public class AnalyticsUpdate {
    private String symbol;
    private double price;
    private double movingAverage;
    private double stdDev;
    private double zScore;
    private boolean anomaly;
    private boolean spike;
    private long timestamp;

    public AnalyticsUpdate() {}

    public AnalyticsUpdate(String symbol, double price, double movingAverage, double stdDev,
                           double zScore, boolean anomaly, boolean spike, long timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.movingAverage = movingAverage;
        this.stdDev = stdDev;
        this.zScore = zScore;
        this.anomaly = anomaly;
        this.spike = spike;
        this.timestamp = timestamp;
    }

    // getters & setters
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getMovingAverage() { return movingAverage; }
    public void setMovingAverage(double movingAverage) { this.movingAverage = movingAverage; }

    public double getStdDev() { return stdDev; }
    public void setStdDev(double stdDev) { this.stdDev = stdDev; }

    public double getzScore() { return zScore; }
    public void setzScore(double zScore) { this.zScore = zScore; }

    public boolean isAnomaly() { return anomaly; }
    public void setAnomaly(boolean anomaly) { this.anomaly = anomaly; }

    public boolean isSpike() { return spike; }
    public void setSpike(boolean spike) { this.spike = spike; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
