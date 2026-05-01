package br.org.caixa.application.repository;

import br.org.caixa.domain.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public Optional<Cliente> findByCpfCnpj(String cpfCnpj) {
        return find("cnpjCpf = ?1", cpfCnpj.trim()).firstResultOptional();
    }
}
