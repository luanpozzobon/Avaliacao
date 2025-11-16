package financeiro.risco.strategy;

import financeiro.risco.FinancialContext;

public final class ValueAtRiskStrategy implements RiskStrategy {
    @Override
    public String calculateRisk(FinancialContext context) {
        double risk = context.getPortfolioValue() * context.getTimeHorizonDays();
        return String.format("[Value at Risk] - Valor de Risco: %.2f", risk);
    }
}
