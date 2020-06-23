package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.domain.Pedido;
import com.lucasnascimento.cursomc.repository.PedidoRepository;
import com.lucasnascimento.cursomc.service.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

  @Autowired
  PedidoRepository pedidoRepository;

  public Pedido buscarPedido(Integer id) {
    Optional<Pedido> pedido = pedidoRepository.findById(id);

    return pedido.orElseThrow(() -> new ObjectNotFoundException(
        "Pedido não encontrado!!! Id: " + id + " Tipo: " + Pedido.class.getName()
    ));
  }
}
