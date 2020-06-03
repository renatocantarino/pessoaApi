package com.cantarino.app.demo.services;

import com.cantarino.app.demo.entities.Pessoa;
import com.cantarino.app.demo.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService  {


    final String pessoaNaoEncontrada = "Pessoa não encontrada";
    final String pessoaJaCadastrada = "Pessoa já cadastrada";

    @Autowired
    private PessoaRepository _repository;

    public List<Pessoa> obterTodos() {
        return _repository.findAll();
    }
    public Pessoa Criar(Pessoa pessoa) {
        JaCadastrado(pessoa);
        return _repository.saveAndFlush(pessoa);
    }

    public Pessoa Update(Pessoa pessoa) {
        return _repository.saveAndFlush(pessoa);
    }

    public Pessoa findById(Long id)
    {
         Optional<Pessoa> pessoa =  _repository.findById(id);
         if(!pessoa.isPresent())
             throw new RuntimeException(pessoaNaoEncontrada);

         return pessoa.get();
    }

    private void JaCadastrado(Pessoa pessoa)
    {
        Optional<Pessoa> _pessoa = _repository.findByCpf(pessoa.getCpf());
        if(_pessoa.isPresent())
            throw new RuntimeException(pessoaJaCadastrada);
    }

    public void Delete(Pessoa retorno) {
        _repository.delete(retorno);
    }
}
