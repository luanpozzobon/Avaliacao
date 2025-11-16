package validators;

import context.ValidationContext;

import java.util.concurrent.ExecutorService;

public class SchemaValidator extends AbstractValidator {
    public SchemaValidator(long timeout, ExecutorService executor) {
        super(timeout, executor);
    }

    protected boolean isCritical() {
        return true;
    }

    @Override
    protected boolean process(ValidationContext context) {
        System.out.println("Validando Schema XML...");

        return true;
    }

    @Override
    protected String getName() {
        return "SchemaValidator";
    }
}