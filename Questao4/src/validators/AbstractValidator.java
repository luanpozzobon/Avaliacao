package validators;

import context.ValidationContext;

import java.util.concurrent.*;

public abstract class AbstractValidator {
    protected AbstractValidator next;
    private final long timeout;
    private final ExecutorService executor;

    public AbstractValidator(long timeoutMillis, ExecutorService executor) {
        this.timeout = timeoutMillis;
        this.executor = executor;
    }

    public AbstractValidator setNext(AbstractValidator next) {
        this.next = next;
        return this;
    }

    public void handle(ValidationContext context) {
        if (context.isCircuitOpen()) {
            System.out.println("CIRCUIT BREAKER: Cadeia interrompida.");
            return;
        }

        if (this.requiresCriticalValidators() && !context.didCriticalValidatorsPass()) {
            System.out.println("SKIP: Pulando " + getName() + " (falha crítica anterior).");
            passToNext(context);
            return;
        }

        boolean success = false;
        Callable<Boolean> task = () -> process(context);
        Future<Boolean> future = executor.submit(task);

        try {
            success = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            context.addError(getName() + " falhou por TIMEOUT de " + timeout + "ms");
        } catch (Exception e) {
            context.addError(getName() + " falhou com exceção: " + e.getMessage());
        }

        if (!success) {
            if (this.isCritical()) {
                context.setCriticalFailure();
            }

            return;
        }

        if (this instanceof IReversibleValidator) {
            context.registerForRollback((IReversibleValidator) this);
        }

        passToNext(context);
    }

    private void passToNext(ValidationContext context) {
        if (this.next != null) {
            this.next.handle(context);
        }
    }

    protected abstract boolean process(ValidationContext context);

    protected abstract String getName();

    protected boolean isCritical() {
        return false;
    }

    protected boolean requiresCriticalValidators() {
        return false;
    }
}
