package br.org.caixa.domain;

import jakarta.persistence.*;

import javax.lang.model.element.Name;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="TBTRANSACTION", uniqueConstraints = {@UniqueConstraint(columnNames = {"CO_TRANSACTION"})})
public class Transaction {

    @Id
    @Column(name = "CO_TRANSACTION")
    private Long id;
    @Column(name = "DT_TRANSACTION", nullable = false)
    private LocalDate data;
    @Column(name = "VR_TRANSACTION", nullable = false)
    private BigDecimal valor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CO_CLIENTE", nullable = false)
    private Cliente cliente;

    public Transaction(Long id, LocalDate data, BigDecimal valor, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.cliente = cliente;
    }

    public Transaction(){
        this.id=null;
        this.data=null;
        this.valor=null;
        this.cliente=null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return  id +
                ";" + data +
                ";" + valor +
                ";" + cliente.getId() ;
    }
}
