package com.cantarino.app.demo.entities;


import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name="Pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name="nome")
    private String nome;

    @Column(name="cpf")
    @CPF(message = "formato inválido")
    private String cpf;


    @Column(name="data_nascimento")
    private LocalDate data_nascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        return id != null ? id.equals(pessoa.id) : pessoa.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
