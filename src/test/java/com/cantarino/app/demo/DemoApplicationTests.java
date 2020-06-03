package com.cantarino.app.demo;

import com.cantarino.app.demo.entities.Pessoa;
import com.cantarino.app.demo.services.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    final String pessoaNaoEncontrada = "Pessoa não encontrada";
    final String pessoaJaCadastrada = "Pessoa já cadastrada";

    @Autowired
    private PessoaService repository;

    @Test
    public void DEVE_SALVAR_E_RECUPERAR() {

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("01913751015");
        pessoa.setNome("teste cantarino");
        pessoa.setData_nascimento(LocalDate.now());

        Pessoa retorno = repository.Criar(pessoa);

        assertEquals(pessoa.getNome(), retorno.getNome());

    }

    @Test
    public void NAO_DEVE_SALVAR_COM_CPF_DUPLICADO() {

        try {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("01913751015");
        pessoa.setNome("teste cantarino");
        pessoa.setData_nascimento(LocalDate.now());

        Pessoa retorno = repository.Criar(pessoa);


        Pessoa P2 = new Pessoa();
        P2.setCpf("01913751015");
        P2.setNome("teste cantarino");
        P2.setData_nascimento(LocalDate.now());

        Pessoa pessoa2 = repository.Criar(P2);
        }
        catch (RuntimeException ex)
        {
            assertEquals(pessoaJaCadastrada , ex.getMessage());
        }

    }



    @Test
    public void DEVE_SALVAR_E_DELETAR() {

        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setCpf("31577344073");
            pessoa.setNome("teste cantarino");
            pessoa.setData_nascimento(LocalDate.now());

            Pessoa retorno = repository.Criar(pessoa);

            repository.Delete(retorno);
            assertEquals(repository.findById(retorno.getId()), 0);
        }
        catch (RuntimeException ex)
        {
            assertEquals(pessoaNaoEncontrada , ex.getMessage());
        }
    }


    @Test
    public void DEVE_RECUPERAR_EDITAR() {

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("37170408040");
        pessoa.setNome("teste cantarino");
        pessoa.setData_nascimento(LocalDate.now());

        Pessoa retorno = repository.Criar(pessoa);

        Pessoa edit = new Pessoa();
        edit.setId(retorno.getId());
        edit.setNome("nome editado");
        edit.setData_nascimento(retorno.getData_nascimento());
        Pessoa retorno2 = repository.Update(pessoa);

        assertEquals(retorno.getId(), retorno2.getId());

    }

    @Test
    public void DEVE_DAR_ERRO_AO_CADASTRAR_PESSOA_CPF_INVALIDO() {

        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setCpf("AAAAAAAAA");
            pessoa.setNome("teste cantarino");
            pessoa.setData_nascimento(LocalDate.now());

            Pessoa retorno = repository.Criar(pessoa);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



    @Test
    void contextLoads() {
    }

}
