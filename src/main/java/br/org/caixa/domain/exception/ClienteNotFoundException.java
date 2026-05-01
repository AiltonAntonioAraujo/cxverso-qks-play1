package br.org.caixa.domain.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("Cliente com o ID: "+id+" não encontrado!");
    }
}
