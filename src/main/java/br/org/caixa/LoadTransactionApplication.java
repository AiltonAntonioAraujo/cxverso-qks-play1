package br.org.caixa;

import br.org.caixa.domain.Cliente;
import br.org.caixa.domain.Transaction;
import br.org.caixa.service.ClienteService;
import br.org.caixa.service.TransactionService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class LoadTransactionApplication implements QuarkusApplication {

    @Inject
    private ClienteService clienteService;

    @Inject
    private TransactionService transactionService;

    @Override
    public int run(String... args) throws Exception {

        Cliente c1 = clienteService.criarCliente("João da Silva", "89076533156");
        Cliente c2 = clienteService.criarCliente("I9 NEGOCIOS PLANEJADOS LTDA", "62994531000134");

        System.out.println("Cliente 1: "+c1.getId());
        System.out.println("Cliente 2: "+c2.getId());

        transactionService.criarTransacao(1L, LocalDate.now(), BigDecimal.valueOf(10.00), c1.getId());
        transactionService.criarTransacao(2L, LocalDate.now(), BigDecimal.valueOf(200.00), c2.getId());
        transactionService.criarTransacao(3L, LocalDate.now().minusDays(15), BigDecimal.valueOf(1500.00), c1.getId());
        transactionService.criarTransacao(4L, LocalDate.now().minusDays(45), BigDecimal.valueOf(200.00), c2.getId());

        System.out.println("CO_TRANSACTION; DT_TRANSACTION; VR_TRANSACTION; CO_CLIENTE");
        System.out.println(transactionService.listAll().stream().map(Transaction::toString).collect(Collectors.joining("\r\n")));
        Quarkus.waitForExit();
        return 0;
    }
}
