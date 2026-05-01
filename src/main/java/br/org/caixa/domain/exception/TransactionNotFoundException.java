package br.org.caixa.domain.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Long codigo) {
        super("Transacao não encontrada com o codigo: "+codigo);
    }
}
