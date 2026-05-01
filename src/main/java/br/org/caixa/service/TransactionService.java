package br.org.caixa.service;

import br.org.caixa.application.repository.TransactionRepository;
import br.org.caixa.domain.Cliente;
import br.org.caixa.domain.Transaction;
import br.org.caixa.domain.exception.TransactionAlreadyException;
import br.org.caixa.domain.exception.TransactionNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class TransactionService {

    @Inject
    private TransactionRepository transactionRepository;

    @Inject
    private ClienteService clienteService;

    @Transactional
    public void criarTransacao(Long codigo, LocalDate data, BigDecimal valor, Long codigoCliente){
        Objects.requireNonNull(codigo, "O código da Transacao não poded ser nulo");
        Objects.requireNonNull(data, "A data da Transacao não pode ser nula");
        Objects.requireNonNull(valor, "O valor da Transacao não pode ser nulo");
        Objects.requireNonNull(codigoCliente, "O código do Cliente não pode ser nulo");

        Optional<Transaction> saved = transactionRepository.findByIdOptional(codigo);
        Cliente clienteSaved=null;

        if(saved.isPresent()){
            throw new TransactionAlreadyException(codigo, data, valor, codigoCliente);
        }else{
            clienteSaved=clienteIsPresent(codigoCliente);
            transactionRepository.persist(new Transaction(codigo, data, valor, clienteSaved));
        }
    }

    public Transaction buscaPorCodigo(Long codigo){

        Optional<Transaction> saved = transactionRepository.findByIdOptional(codigo);
        if(saved.isPresent()){
            return saved.get();
        }else{
            throw new TransactionNotFoundException(codigo);
        }

    }

    @Transactional
    public List<Transaction> listAll(){
        return transactionRepository.listAll();
    }

    private Cliente clienteIsPresent(Long codigoCliente){
        return clienteService.consultaPorId(codigoCliente);
    }
}
