package validators;

import context.ValidationContext;

import java.util.concurrent.ExecutorService;

public class DatabaseValidator extends AbstractValidator implements IReversibleValidator {
    public DatabaseValidator(long timeout, ExecutorService executor) { super(timeout, executor); }
    protected String getName() { return "DatabaseValidator"; }

    protected boolean process(ValidationContext context) {
        System.out.println("Validando duplicidade no Banco de Dados...");
        System.out.println("... Inserindo/bloqueando registro " + context.getNfe().id());
        return true;
    }

    @Override
    public void rollback(ValidationContext context) {
        System.out.println("ROLLBACK: Deletando registro " + context.getNfe().id() + " do DB.");
    }
}