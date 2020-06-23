package com.lucasnascimento.cursomc;

import com.lucasnascimento.cursomc.domain.Categoria;
import com.lucasnascimento.cursomc.domain.Cidade;
import com.lucasnascimento.cursomc.domain.Cliente;
import com.lucasnascimento.cursomc.domain.Endereco;
import com.lucasnascimento.cursomc.domain.Estado;
import com.lucasnascimento.cursomc.domain.ItemPedido;
import com.lucasnascimento.cursomc.domain.Pagamento;
import com.lucasnascimento.cursomc.domain.PagamentoComBoleto;
import com.lucasnascimento.cursomc.domain.PagamentocomCartao;
import com.lucasnascimento.cursomc.domain.Pedido;
import com.lucasnascimento.cursomc.domain.Produto;
import com.lucasnascimento.cursomc.domain.enums.EstadoPagamento;
import com.lucasnascimento.cursomc.domain.enums.TipoCliente;
import com.lucasnascimento.cursomc.repository.CategoriaRepository;
import com.lucasnascimento.cursomc.repository.CidadeRepository;
import com.lucasnascimento.cursomc.repository.ClienteRepository;
import com.lucasnascimento.cursomc.repository.EnderecoRepository;
import com.lucasnascimento.cursomc.repository.EstadoRepository;
import com.lucasnascimento.cursomc.repository.ItemPedidoRepository;
import com.lucasnascimento.cursomc.repository.PagamentoRepository;
import com.lucasnascimento.cursomc.repository.PedidoRepository;
import com.lucasnascimento.cursomc.repository.ProdutoRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

  public static void main(String[] args) {
    SpringApplication.run(CursomcApplication.class, args);
  }


  @Override
  public void run(String... args) throws Exception {

    Categoria cat1 = new Categoria(null, "Informática");
    Categoria cat2 = new Categoria(null, "Escritório");
    Categoria cat3 = new Categoria(null, "Decoração");
    Categoria cat4 = new Categoria(null, "Cama,Mesa e Banho");

    Produto p1 = new Produto(null, "Computador", 2000.00);
    Produto p2 = new Produto(null, "Impressora", 800.00);
    Produto p3 = new Produto(null, "Mouse", 80.00);
    Produto p4 = new Produto(null, "Cadeira Gamer", 950.00);
    Produto p5 = new Produto(null, "Quadro", 200.00);
    Produto p6 = new Produto(null, "Boneca", 183.00);

    Cidade c1 = new Cidade(null, "Campinas");
    Cidade c2 = new Cidade(null, "São Paulo");
    Cidade c3 = new Cidade(null, "Uberlândia");

    Estado e1 = new Estado(null, "São Paulo");
    Estado e2 = new Estado(null, "Minas Gerais");

    Cliente cli1 = new Cliente(1, "Maria", "maria@gmail.com", "35345785266", TipoCliente.PESSOAFISICA);

    Endereco end1 = new Endereco(null, "rua Flores", "300", "apto 303", "jardim", "38220834", cli1,
        c1);
    Endereco end2 = new Endereco(null, "rua 5", "256", "Casa 2", "Centro", "25666789", cli1, c2);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    p1.setCategorias(Arrays.asList(cat1));
    p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
    p3.getCategorias().addAll(Arrays.asList(cat1));
    p4.getCategorias().addAll(Arrays.asList(cat1, cat2, cat3));
    p5.getCategorias().addAll(Arrays.asList(cat3));

    cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4));
    cat2.getProdutos().addAll(Arrays.asList(p2, p4));
    cat3.getProdutos().addAll(Arrays.asList(p4, p5));

    c1.setEstado(e1);
    c2.setEstado(e1);
    c3.setEstado(e2);
    cli1.getTelefones().addAll(Arrays.asList("992192260", "994418300"));
    cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

    categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
    produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
    estadoRepository.saveAll(Arrays.asList(e1, e2));
    cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
    clienteRepository.saveAll(Arrays.asList(cli1));
    enderecoRepository.saveAll(Arrays.asList(end1, end2));

    Pedido pedido1 = new Pedido(null, sdf.parse("30/04/2020 22:22"), cli1, end1);
    Pedido pedido2 = new Pedido(null, sdf.parse("01/05/2020 15:10"), cli1, end2);

    Pagamento pgto1 = new PagamentocomCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
    Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
        sdf.parse("30/04/2020 22:22"), sdf.parse("30/6/2020 22:22"));

    cli1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
    pedido1.setPagamento(pgto1);
    pedido2.setPagamento(pgto2);


    pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
    pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));

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
