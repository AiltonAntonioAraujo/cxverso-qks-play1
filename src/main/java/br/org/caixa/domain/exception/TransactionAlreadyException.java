package br.org.caixa.domain.exception;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionAlreadyException extends RuntimeException {
    public TransactionAlreadyException(Long codigo, LocalDate data, BigDecimal valor, Long codigoCliente) {
        super("Transacao já existe com os dados [codigo: "+codigo+", data: "+data+", valor: "+valor+", codigoCliente: "+codigoCliente+"]");
    }
}
