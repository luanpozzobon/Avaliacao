package financeiro.risco.strategy;

import financeiro.risco.FinancialContext;

public final class ExpectedShortfallStrategy implements RiskStrategy {
    @Override
    public String calculateRisk(FinancialContext context) {
        double expectedShortfall = context.getPortfolioValue() * Math.pow(context.getMarketVolatility(), 2) * context.getTimeHorizonDays();
        return String.format("[Expected Shortfall] - Risco Esperado: %.2f", expectedShortfall);
    }
}
