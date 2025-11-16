package financeiro.risco.strategy;

import financeiro.risco.FinancialContext;

public final class StressTestingStrategy implements RiskStrategy {
    @Override
    public String calculateRisk(FinancialContext context) {
        double stressTesting = context.getPortfolioValue() * 0.4;
        return String.format("[Stress Testing] - Perda em Risco: %.2f", stressTesting);
    }
}
