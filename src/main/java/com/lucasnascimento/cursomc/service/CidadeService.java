package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.domain.Cidade;
import com.lucasnascimento.cursomc.repository.CidadeRepository;
import com.lucasnascimento.cursomc.service.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

  @Autowired
  CidadeRepository cidadeRepository;

  public Cidade buscarCidade(Integer id) {
    Optional<Cidade> obj = cidadeRepository.findById(id);

    return obj.orElseThrow(() -> new ObjectNotFoundException(
       "Objeto nãoi encontrado! Id: " + id + ", tipo: " + Cidade.class.getName()
    ));
  }

}
