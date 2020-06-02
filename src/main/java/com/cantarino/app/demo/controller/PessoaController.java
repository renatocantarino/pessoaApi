package com.cantarino.app.demo.controller;

import com.cantarino.app.demo.entities.Pessoa;
import com.cantarino.app.demo.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PessoaController {


    @Autowired
    private PessoaService _pessoaService;

    @GetMapping("/api/pessoa")
    public List<Pessoa> All() {
        return  _pessoaService.obterTodos();
    }

    @PostMapping("/api/pessoa")
    public Pessoa Criar(@Valid @RequestBody Pessoa pessoa) {
        try
        {
            return _pessoaService.CriarOrUpdate(pessoa);
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
        return null;
    }

    @PostMapping("/api/pessoa/{id}")
    public Pessoa Update(@Valid @RequestBody Pessoa pessoa , @PathVariable("id") long id) {

        Pessoa retorno = _pessoaService.findById(id);
        pessoa.setId(retorno.getId());

        return _pessoaService.CriarOrUpdate(pessoa);
    }

    @DeleteMapping("/api/pessoa/{id}")
    public void Delete(@PathVariable("id") long id) {
         _pessoaService.Delete(_pessoaService.findById(id));
    }
}
