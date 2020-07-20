package com.lucasnascimento.cursomc.service;

import com.lucasnascimento.cursomc.domain.*;
import com.lucasnascimento.cursomc.domain.enums.EstadoPagamento;
import com.lucasnascimento.cursomc.domain.enums.TipoCliente;
import com.lucasnascimento.cursomc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public void instantiateTestDatabase() throws ParseException {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Decoração");
        Categoria cat4 = new Categoria(null, "Cama,Mesa e Banho");
        Categoria cat5 = new Categoria(null, "Brinquedos");
        Categoria cat6 = new Categoria(null, "Jardinagem");
        Categoria cat7 = new Categoria(null, "Ferramentas");
        Categoria cat8 = new Categoria(null, "Eletrônicos");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        Produto p4 = new Produto(null, "Cadeira Gamer", 950.00);
        Produto p5 = new Produto(null, "Quadro", 200.00);
        Produto p6 = new Produto(null, "Boneca", 183.00);
        Produto p7 = new Produto(null, "TV true color", 1200.00);
        Produto p8 = new Produto(null, "Roçadeira", 800.00);
        Produto p9 = new Produto(null, "Abajour", 100.00);
        Produto p10 = new Produto(null, "Pendente", 180.00);
        Produto p11 = new Produto(null, "Toalha de Rosto", 90.00);

        p1.getCategorias().addAll(Arrays.asList(cat1, cat8));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat8));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat2, cat8));
        p4.getCategorias().addAll(Arrays.asList(cat1, cat2, cat3));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat5));
        p7.getCategorias().addAll(Arrays.asList(cat8));
        p8.getCategorias().addAll(Arrays.asList(cat6, cat7));
        p9.getCategorias().addAll(Arrays.asList(cat3));
        p10.getCategorias().addAll(Arrays.asList(cat3));
        p11.getCategorias().addAll(Arrays.asList(cat4));


        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p4, p5, p9, p10));
        cat4.getProdutos().addAll(Arrays.asList(p11));
        cat5.getProdutos().addAll(Arrays.asList(p6));
        cat6.getProdutos().addAll(Arrays.asList(p8));
        cat7.getProdutos().addAll(Arrays.asList(p8));
        cat8.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5,cat6, cat7, cat8));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));


        Estado e1 = new Estado(null, "São Paulo");
        Estado e2 = new Estado(null, "Minas Gerais");
        Estado e3 = new Estado(null, "Rio Grande do Sul");

        Cidade c1 = new Cidade(null, "Campinas", e1);
        Cidade c2 = new Cidade(null, "São Paulo", e1);
        Cidade c3 = new Cidade(null, "Uberlândia", e2);
        Cidade c4 = new Cidade(null, "Porto Alegre", e3);
        Cidade c5 = new Cidade(null, "Artur Nogueira", e1);

        estadoRepository.saveAll(Arrays.asList(e1, e2, e3));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

        Cliente cli1 = new Cliente(1, "Maria", "maria2@gmail.com", "65731457042", TipoCliente.PESSOAFISICA);
        Cliente cli2 = new Cliente(2, "Genésio", "genesio2@gmail.com", "14486159000172", TipoCliente.PESSOAJURIDICA);
        Cliente cli3 = new Cliente(3, "Juakin", "juakin2@gmail.com", "99379696000176", TipoCliente.PESSOAJURIDICA);
        Cliente cli4 = new Cliente(4, "Alete", "aletebakurau2@gmail.com", "98817914053", TipoCliente.PESSOAFISICA);
        Cliente cli5 = new Cliente(5, "Nikita", "nikita2@gmail.com", "70897188080", TipoCliente.PESSOAFISICA);

        Endereco end1 = new Endereco(null, "rua Flores", "300", "apto 303", "jardim", "38220834", cli1,
                c1);
        Endereco end2 = new Endereco(null, "rua 5", "256", "Casa 2", "Centro", "25666789", cli2, c2);

        Endereco end3 = new Endereco(null, "rua Santiago Careca", "301", "Em frente à praça", "Jardim Amaro", "13160000", cli3, c5);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");


        c1.setEstado(e1);
        c2.setEstado(e1);
        c3.setEstado(e2);
        c4.setEstado(e3);
        cli1.getTelefones().addAll(Arrays.asList("992192260", "994418300"));
        cli2.getTelefones().addAll(Arrays.asList("38772269", "995664533"));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
        enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

        Pedido pedido1 = new Pedido(null, sdf.parse("30/04/2020 22:22"), cli1, end1);
        Pedido pedido2 = new Pedido(null, sdf.parse("01/05/2020 15:10"), cli2, end2);
        Pedido pedido3 = new Pedido(null, sdf.parse("01/05/2020 15:10"), cli3, end3);

        Pagamento pgto1 = new PagamentocomCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
                sdf.parse("30/04/2020 22:22"), sdf.parse("30/6/2020 22:22"));
        Pagamento pgto3 = new PagamentocomCartao(null, EstadoPagamento.QUITADO, pedido3, 6);

        cli1.getPedidos().addAll(Arrays.asList(pedido1));
        cli2.getPedidos().addAll(Arrays.asList(pedido2));
        cli3.getPedidos().addAll(Arrays.asList(pedido3));
        pedido1.setPagamento(pgto1);
        pedido2.setPagamento(pgto2);
        pedido3.setPagamento(pgto3);

        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));
        pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2, pgto3));

        ItemPedido ip1 = new ItemPedido(pedido1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(pedido1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(pedido2, p2, 10.00, 1, 200.00);

        pedido1.getItens().addAll(Arrays.asList(ip1, ip2));
        pedido2.getItens().addAll(Arrays.asList(ip3));


        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
