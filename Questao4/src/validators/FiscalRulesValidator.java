package validators;

import context.ValidationContext;

import java.util.concurrent.ExecutorService;

public class FiscalRulesValidator extends AbstractValidator {
    public FiscalRulesValidator(long timeout, ExecutorService executor) {
        super(timeout, executor);
    }

    protected boolean requiresCriticalValidators() {
        return true;
    }

    @Override
    protected String getName() {
        return "FiscalRulesValidator";
    }

    @Override
    protected boolean process(ValidationContext context) {
        System.out.println("Validando Regras Fiscais (Impostos)...");

        return true;
    }
}
