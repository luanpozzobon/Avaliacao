package bancario.legacy;

import java.util.Map;

public sealed interface ILegacyBank permits LegacyBank {
    Map<String, Object> processTransaction(Map<String, Object> parameters);
}
