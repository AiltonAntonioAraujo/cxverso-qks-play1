package br.org.caixa.domain.exception;

public class ClienteAlreadyExcetion extends RuntimeException {
    public ClienteAlreadyExcetion(String nome, String cpf) {
        super("Cliente com Nome: " + nome + " e CpfCnpj: "+cpf+ ", já cadastrado");
    }
}
