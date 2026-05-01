package br.org.caixa;

import br.org.caixa.service.ClienteService;
import br.org.caixa.service.TransactionService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class LoadTransactionMain {

    static void main(String[] args) {
        Quarkus.run(LoadTransactionApplication.class , args);

    }
}
