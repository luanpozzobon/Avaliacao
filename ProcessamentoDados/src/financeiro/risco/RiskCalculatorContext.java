package financeiro.risco;

import financeiro.risco.strategy.RiskStrategy;

public class RiskCalculatorContext {
    private RiskStrategy strategy;

    public void setStrategy(RiskStrategy strategy) {
        this.strategy = strategy;
    }

    public String calculateRisk(FinancialContext context) {
        return this.strategy.calculateRisk(context);
    }
}
