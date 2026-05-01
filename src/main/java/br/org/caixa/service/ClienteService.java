package br.org.caixa.service;

import br.org.caixa.application.repository.ClienteRepository;
import br.org.caixa.domain.Cliente;
import br.org.caixa.domain.exception.ClienteAlreadyExcetion;
import br.org.caixa.domain.exception.ClienteNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente criarCliente(String nome, String cpfCnpj){
        Objects.requireNonNull(nome, "O nome do cliente não pode ser nulo");
        Objects.requireNonNull(cpfCnpj, "O cpfCnpj do cliente não pode ser nulo");

        Optional<Cliente> saved = clienteRepository.findByCpfCnpj(cpfCnpj);
        Cliente novo = null;
        if(saved.isPresent()){
            throw new ClienteAlreadyExcetion(nome, cpfCnpj);
        }else{
            novo = new Cliente(nome, cpfCnpj);
            clienteRepository.persist(novo);
        }
        return novo;
    }

    @Transactional
    public Cliente consultaPorId(Long id){
        Objects.requireNonNull(id, "O código do cliente não pode ser nulo");

        Optional<Cliente> saved = clienteRepository.findByIdOptional(id);

        if(saved.isPresent()){
            return saved.get();
        }else{
            throw new ClienteNotFoundException(id);
        }
    }

}
