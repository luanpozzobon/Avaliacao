package bancario.legacy;

import java.util.HashMap;
import java.util.Map;

public final class LegacyBank implements ILegacyBank {

    @Override
    public Map<String, Object> processTransaction(Map<String, Object> parameters) {
        if (!parameters.containsKey("TRANSACTION_ID")) {
            return new HashMap<>() {{
                put("STATUS", "ERROR");
                put("MESSAGE", "Transaction ID is required");
            }};
        }

        return new HashMap<>() {{
            put("STATUS", "SUCCESS");
            put("AUTHORIZATION_CODE", "1234567890");
            put("MESSAGE", "Transaction processed successfully");
        }};
    }
}
