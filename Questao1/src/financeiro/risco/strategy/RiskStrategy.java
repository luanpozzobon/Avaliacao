package financeiro.risco.strategy;

import financeiro.risco.FinancialContext;

public sealed interface RiskStrategy permits ExpectedShortfallStrategy,
                                             StressTestingStrategy,
                                             ValueAtRiskStrategy {
    String calculateRisk(FinancialContext context);
}
