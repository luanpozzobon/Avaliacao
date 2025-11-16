import financeiro.risco.FinancialContext;
import financeiro.risco.RiskCalculatorContext;
import financeiro.risco.strategy.ExpectedShortfallStrategy;
import financeiro.risco.strategy.RiskStrategy;
import financeiro.risco.strategy.StressTestingStrategy;
import financeiro.risco.strategy.ValueAtRiskStrategy;

public class Main {
    public static void main(String[] args) {
        FinancialContext context = new FinancialContext(100000, 0.05, 30);

        RiskStrategy varStrategy = new ValueAtRiskStrategy();
        RiskStrategy esStrategy = new ExpectedShortfallStrategy();
        RiskStrategy stStrategy = new StressTestingStrategy();

        RiskCalculatorContext riskCalculator = new RiskCalculatorContext();

        System.out.println("\nCenário 1: Análise VaR");
        riskCalculator.setStrategy(varStrategy);
        String resultVaR = riskCalculator.calculateRisk(context);
        System.out.println(resultVaR);

        System.out.println("\nCenário 2: Análise Expected Shortfall");
        riskCalculator.setStrategy(esStrategy);
        String resultES = riskCalculator.calculateRisk(context);
        System.out.println(resultES);

        System.out.println("\nCenário 3: Análise Stress Testing");
        riskCalculator.setStrategy(stStrategy);
        String resultStress = riskCalculator.calculateRisk(context);
        System.out.println(resultStress);
    }
}