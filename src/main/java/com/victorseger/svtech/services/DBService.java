package com.victorseger.svtech.services;

import com.victorseger.svtech.domain.*;
import com.victorseger.svtech.domain.enums.EstadoPagamento;
import com.victorseger.svtech.domain.enums.Perfil;
import com.victorseger.svtech.domain.enums.TipoCliente;
import com.victorseger.svtech.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

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

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    public void instantiateDataBase() throws ParseException {
        Role role = new Role();
        role.setRole("ROLE_ADMIN");
        roleRepository.save(role);
        Role rol2 = new Role();
        rol2.setRole("ROLE_USER");
        roleRepository.save(rol2);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User admin = new User("Administrador","admin","vicktor.junior@gmail.com",bCryptPasswordEncoder.encode("admin"),true, roles);
        userRepository.save(admin);
        roles.clear();
        roles.add(rol2);
        User user = new User("Vendedor","user","vicktor.junior@gmail.com",bCryptPasswordEncoder.encode("user"),true, roles);
        userRepository.save(user);

        Categoria cat1 = new Categoria(null, "informática");
        Categoria cat2 = new Categoria(null, "escritório");
        Categoria cat3 = new Categoria(null, "cama");
        Categoria cat4 = new Categoria(null, "mesa");
        Categoria cat5 = new Categoria(null, "banho");
        Categoria cat6 = new Categoria(null, "decoração");
        Categoria cat7 = new Categoria(null, "perfumaria");


        Produto p1 = new Produto(null, "Computador", 10,2000.00);
        Produto p2 = new Produto(null, "Impressora",10, 800.00);
        Produto p3 = new Produto(null, "Mouse",10, 80.00);
        Produto p4 = new Produto(null, "mesa",10, 300.00);
        Produto p5 = new Produto(null, "toalha",10, 50.00);
        Produto p6 = new Produto(null, "colcha",10, 200.00);
        Produto p7 = new Produto(null, "tv true color",10, 1200.00);
        Produto p8 = new Produto(null, "roçadeira",10, 800.00);
        Produto p9 = new Produto(null, "abajour",10, 100.00);
        Produto p10 = new Produto(null, "pendente",10, 180.00);
        Produto p11 = new Produto(null, "shampoo",10, 90.00);




        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2,p4));
        cat3.getProdutos().addAll(Arrays.asList(p5,p6));
        cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9,p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3, p4, p5, p6, p7, p8, p9, p10, p11));
        
        Estado est1 = new Estado(null, "Rio Grande do Sul");
        Estado est2 = new Estado(null, "Santa Catarina");

        Cidade c1 = new Cidade(null,"Porto Alegre", est1);
        Cidade c2 = new Cidade(null,"Florianópolis", est2);
        Cidade c3 = new Cidade(null, "Canoas", est1);

        est1.getCidades().addAll(Arrays.asList(c1, c3));
        est2.getCidades().addAll(Arrays.asList(c2));

        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));


        Cliente cli1 = new Cliente(null, "Usuário comum", "vicktor.junior@hotmail.com", "11111111111", TipoCliente.PESSOAFISICA, bCryptPasswordEncoder.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("111111111","111111111"));

        Cliente cli2 = new Cliente(null, "Ana Costa", "victor.seger@outlook.com", "28616481800", TipoCliente.PESSOAFISICA, bCryptPasswordEncoder.encode("123"));
        cli2.getTelefones().addAll(Arrays.asList("27363323","93838393"));
        cli2.addPerfil(Perfil.ADMIN);//adicionando o perfil de admin ao usuário

        Endereco e1 = new Endereco(null,"Endereço padrão", "0", "", "", "00000000", cli1, c1);
        Endereco e2 = new Endereco(null,"Avenida Matos", "105", "Apto 800", "Centro", "38777012", cli1, c2);
        Endereco e3 = new Endereco(null,"Travessa José", "1231", "", "Centro", "38777012", cli2, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1));
        cli2.getEnderecos().addAll(Arrays.asList(e3,e2));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2018 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2018 19:37"), cli1, e1);
        Pedido ped3 = new Pedido(null, (sdf.parse("21/10/2018 12:00")), cli1, e1);
        Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pgto1);

        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),null);
        ped2.setPagamento(pgto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1,ped2, ped3));
        pagamentoRepository.saveAll(Arrays.asList(pgto1,pgto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
        ItemPedido ip4 = new ItemPedido(ped3, p1, 0.00, 1, 2000.00);


        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        ped3.getItens().addAll(Arrays.asList(ip4));

        double soma = 0;
        for (ItemPedido itemPedido : ped1.getItens()) {
             soma += itemPedido.getPreco();
        }
        ped1.setValorTotal(soma);
        soma = 0;
        for (ItemPedido itemPedido : ped2.getItens()) {
            soma += itemPedido.getPreco();
        }
        ped2.setValorTotal(soma);
        soma = 0;
        for (ItemPedido itemPedido : ped3.getItens()) {
            soma += itemPedido.getPreco();
        }
        ped3.setValorTotal(soma);
        pedidoRepository.saveAll(Arrays.asList(ped1,ped2,ped3));
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));
        p3.getItens().addAll(Arrays.asList(ip4));

        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3,ip4));
    }

}
