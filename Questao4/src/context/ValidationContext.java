package context;

import models.NFeDocument;
import validators.IReversibleValidator;

import java.util.*;

public class ValidationContext {
    private static final int FAILURE_THRESHOLD = 3;

    private final NFeDocument nfe;
    private final List<String> errors;
    private int failureCount;

    private final Stack<IReversibleValidator> rollbackStack;

    private boolean criticalValidatorsPassed;

    public ValidationContext(NFeDocument nfe) {
        this.nfe = nfe;
        this.errors = new ArrayList<>();
        this.failureCount = 0;
        this.rollbackStack = new Stack<>();
        this.criticalValidatorsPassed = true;
    }

    public NFeDocument getNfe() {
        return this.nfe;
    }

    public void addError(String error) {
        this.errors.add(error);
        this.failureCount++;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public int getFailureCount() {
        return this.failureCount;
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    public boolean isCircuitOpen() {
        return this.failureCount >= FAILURE_THRESHOLD;
    }

    public void setCriticalFailure() {
        this.criticalValidatorsPassed = false;
    }

    public boolean didCriticalValidatorsPass() {
        return this.criticalValidatorsPassed;
    }

    // --- Métodos de Rollback (Command) ---
    public void registerForRollback(IReversibleValidator validator) {
        this.rollbackStack.push(validator);
    }

    public Stack<IReversibleValidator> getRollbackStack() {
        return this.rollbackStack;
    }
}
