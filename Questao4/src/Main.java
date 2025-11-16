import context.ValidationContext;
import models.NFeDocument;
import validators.*;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        AbstractValidator chainHead = new SchemaValidator(1000, executor);

        chainHead.setNext(new CertificateValidator(2000, executor))
                 .setNext(new FiscalRulesValidator(500, executor))
                 .setNext(new DatabaseValidator(1500, executor))
                 .setNext(new SefazValidator(3000, executor));


        NFeDocument doc = new NFeDocument("NFe-42", "<xml>...</xml>");
        ValidationContext context = new ValidationContext(doc);

        System.out.println("--- Iniciando Validação ---");

        chainHead.handle(context);

        System.out.println("\n--- Validação Concluída ---");

        if (context.hasErrors()) {
            System.out.println("Falha na validação. Iniciando rollbacks...");
            Stack<IReversibleValidator> rollbackStack = context.getRollbackStack();
            while (!rollbackStack.isEmpty()) {
                rollbackStack.pop().rollback(context);
            }
        } else {
            System.out.println("NFe APROVADA com sucesso.");
        }

        shutdownExecutor(executor);
    }

    private static void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}