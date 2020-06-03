package com.cantarino.app.demo.controller;

import com.cantarino.app.demo.entities.Pessoa;
import com.cantarino.app.demo.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity Criar(@Valid @RequestBody Pessoa pessoa) {
        try
        {
            return  ResponseEntity.ok().body(_pessoaService.Criar(pessoa));
        }
        catch (RuntimeException ex)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                  .body(ex.getMessage());
        }

    }

    @PostMapping("/api/pessoa/{id}")
    public ResponseEntity Update(@Valid @RequestBody Pessoa pessoa , @PathVariable("id") long id) {

        try
        {
            pessoa.setId(id);
            return  ResponseEntity.ok().body(_pessoaService.Update(pessoa));
        }
        catch (RuntimeException ex)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ex.getMessage());
        }
    }

    @DeleteMapping("/api/pessoa/{id}")
    public ResponseEntity Delete(@PathVariable("id") long id) {

        try
        {
            _pessoaService.Delete(_pessoaService.findById(id));
            return  ResponseEntity.ok().body("Pessoa removida");
        }
        catch (RuntimeException ex)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ex.getMessage());
        }
    }
}
