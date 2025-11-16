package validators;

import context.ValidationContext;

public interface IReversibleValidator {
    void rollback(ValidationContext context);
}
