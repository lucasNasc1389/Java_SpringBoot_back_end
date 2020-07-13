package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.domain.ItemPedido;
import com.lucasnascimento.cursomc.domain.PagamentoComBoleto;
import com.lucasnascimento.cursomc.domain.Pedido;
import com.lucasnascimento.cursomc.domain.enums.EstadoPagamento;
import com.lucasnascimento.cursomc.repository.*;
import com.lucasnascimento.cursomc.service.exceptions.ObjectNotFoundException;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    BoletoService boletoService;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public Pedido buscarPedido(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado!!! Id: " + id + " Tipo: " + Pedido.class.getName()
        ));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setData(new Date());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoNomBoleto(pagto, pedido.getData());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;
    }
}
