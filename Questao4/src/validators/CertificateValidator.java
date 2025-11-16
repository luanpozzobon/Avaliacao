package validators;

import context.ValidationContext;

import java.util.concurrent.ExecutorService;

public class CertificateValidator extends AbstractValidator {
    public CertificateValidator(long timeout, ExecutorService executor) {
        super(timeout, executor);
    }

    protected boolean isCritical() {
        return true;
    }

    @Override
    protected String getName() {
        return "CertificateValidator";
    }

    @Override
    protected boolean process(ValidationContext context) {
        System.out.println("Validando Certificado Digital...");

        return true;
    }
}
