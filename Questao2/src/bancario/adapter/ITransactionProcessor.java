package bancario.adapter;

import bancario.models.AuthorizationResponse;

public interface ITransactionProcessor {
    AuthorizationResponse autorizar(final String cartao,
                                    final double valor,
                                    final String moeda);
}
