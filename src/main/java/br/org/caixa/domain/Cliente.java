package br.org.caixa.domain;

import jakarta.persistence.*;

@Entity
@Table(name="TBCLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CO_CLIENTE")
    private Long Id;
    @Column(name="NO_CLIENTE", nullable = false)
    private String name;
    @Column(name="CO_CNPJCPF", nullable = false)
    private String cnpjCpf;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public Cliente(String name, String cnpjCpf) {
        this.name = name;
        this.cnpjCpf = cnpjCpf;
    }

    public Cliente(Long id, String name, String cnpjCpf) {
        Id = id;
        this.name = name;
        this.cnpjCpf = cnpjCpf;
    }

    public Cliente() {
        this.Id = null;
        this.cnpjCpf = null;
        this.name = null;
    }
}
