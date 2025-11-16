package validators;

import context.ValidationContext;

import java.util.concurrent.ExecutorService;

public class SefazValidator extends AbstractValidator {
    public SefazValidator(long timeout, ExecutorService executor) { super(timeout, executor); }
    protected String getName() { return "SefazValidator"; }

    protected boolean requiresCriticalValidators() { return true; }

    protected boolean process(ValidationContext context) {
        System.out.println("Validando com serviço da SEFAZ (online)...");
        return true;
    }
}
