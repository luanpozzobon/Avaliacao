package bancario.adapter;

import bancario.legacy.ILegacyBank;
import bancario.models.AuthorizationResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TransactionProcessorAdapter implements ITransactionProcessor {
    private static final Map<String, Integer> CURRENCIES = new HashMap<>() {{
        put("USD", 1);
        put("EUR", 2);
        put("BRL", 3);
    }};

    private final ILegacyBank legacyBank;

    public TransactionProcessorAdapter(ILegacyBank legacyBank) {
        this.legacyBank = legacyBank;
    }

    @Override
    public AuthorizationResponse autorizar(final String cartao,
                                           final double valor,
                                           final String moeda) {
        Map<String, Object> parameters;
        try {
            parameters = this.convertParamaters(cartao, valor, moeda);
        } catch (Exception e) {
            return new AuthorizationResponse(false, "", e.getMessage());
        }

        final Map<String, Object> response = legacyBank.processTransaction(parameters);
        return this.convertResponse(response);
    }

    private Map<String, Object> convertParamaters(final String cartao,
                                                  final double valor,
                                                  final String moeda)
    throws Exception {
        final Map<String, Object> parameters = new HashMap<>() {{
            put("CARD_NUMBER", cartao);
            put("AMOUNT", valor);
        }};

        final int currencyCode = CURRENCIES.getOrDefault(moeda, 0);
        if (currencyCode == 0) {
            throw new Exception("Currency not supported");
        }
        parameters.put("CURRENCY", currencyCode);

        final String transactionId = String.valueOf(System.currentTimeMillis()) + UUID.randomUUID();
        parameters.put("TRANSACTION_ID", transactionId);

        return parameters;
    }

    private AuthorizationResponse convertResponse(final Map<String, Object> response) {
        final boolean authorized = response.get("STATUS").equals("SUCCESS");
        final String code = (String) response.getOrDefault("AUTHORIZATION_CODE", "");
        final String message = (String) response.getOrDefault("MESSAGE", "");

        if (!authorized) return new AuthorizationResponse(false, "", "Unknown error!");

        return new AuthorizationResponse(authorized, code, message);
    }
}
