import bancario.adapter.ITransactionProcessor;
import bancario.adapter.TransactionProcessorAdapter;
import bancario.legacy.ILegacyBank;
import bancario.legacy.LegacyBank;
import bancario.models.AuthorizationResponse;

public class Main {
    public static void main(String[] args) {
        final ILegacyBank legacyBank = new LegacyBank();
        final ITransactionProcessor transactionProcessor = new TransactionProcessorAdapter(legacyBank);

        AuthorizationResponse response = transactionProcessor.autorizar("1234567890", 100.0, "USD");
        System.out.println(response);

        response = transactionProcessor.autorizar("1234567890", 100.0, "EUR");
        System.out.println(response);

        response = transactionProcessor.autorizar("1234567890", 100.0, "BRL");
        System.out.println(response);

        response = transactionProcessor.autorizar("1234567890", 100.0, "JPY");
        System.out.println(response);
    }
}