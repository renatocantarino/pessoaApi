package com.cantarino.app.demo.services;

import com.cantarino.app.demo.entities.Pessoa;
import com.cantarino.app.demo.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService  {

    @Autowired
    private PessoaRepository _repository;
    public List<Pessoa> obterTodos() {
        return _repository.findAll();
    }
    public Pessoa CriarOrUpdate(Pessoa pessoa) {
        return _repository.saveAndFlush(pessoa);
    }

    public Pessoa findById(Long id)
    {
         Optional<Pessoa> pessoa =  _repository.findById(id);
         if(!pessoa.isPresent())
             throw new RuntimeException("Pessoa nao encontrada");

         return pessoa.get();
    }


    public void Delete(Pessoa retorno) {
        _repository.delete(retorno);
    }
}
