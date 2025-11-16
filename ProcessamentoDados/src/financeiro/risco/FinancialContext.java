package financeiro.risco;

public class FinancialContext {
    private final double portfolioValue;
    private final double marketVolatility;
    private final int timeHorizonDays;

    public FinancialContext(final double portfolioValue,
                            final double marketVolatility,
                            final int timeHorizonDays) {
        this.portfolioValue = portfolioValue;
        this.marketVolatility = marketVolatility;
        this.timeHorizonDays = timeHorizonDays;
    }

    public double getPortfolioValue() {
        return this.portfolioValue;
    }

    public double getMarketVolatility() {
        return this.marketVolatility;
    }

    public int getTimeHorizonDays() {
        return this.timeHorizonDays;
    }
}
