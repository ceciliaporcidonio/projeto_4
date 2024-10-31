package com.seu_projeto;

import com.seu_projeto.cliente.Cliente;
import com.seu_projeto.cliente.ClienteService;
import com.seu_projeto.cliente.dao.ClienteDAOPostgres;
import com.seu_projeto.produto.Produto;
import com.seu_projeto.produto.ProdutoService;
import com.seu_projeto.produto.dao.ProdutoDAOPostgres;
import com.seu_projeto.venda.ItemVenda;
import com.seu_projeto.venda.Venda;
import com.seu_projeto.venda.VendaService;
import com.seu_projeto.venda.dao.VendaDAOPostgres;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ClienteService clienteService = new ClienteService(new ClienteDAOPostgres());
            ProdutoService produtoService = new ProdutoService(new ProdutoDAOPostgres());
            VendaService vendaService = new VendaService(new VendaDAOPostgres());

            while (true) {
                exibirMenuPrincipal();
                int opcao = lerOpcao(scanner);

                switch (opcao) {
                    case 1 -> gerenciarCliente(clienteService, scanner);
                    case 2 -> gerenciarProduto(produtoService, scanner);
                    case 3 -> gerenciarVenda(vendaService, clienteService, produtoService, scanner);
                    case 4 -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Gerenciar Cliente");
        System.out.println("2. Gerenciar Produto");
        System.out.println("3. Gerenciar Venda");
        System.out.println("4. Sair");
    }

    private static int lerOpcao(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Informe um número:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void gerenciarCliente(ClienteService clienteService, Scanner scanner) {
        System.out.println("Cliente: 1. Cadastrar 2. Consultar 3. Alterar 4. Excluir 5. Listar Todos");
        int opcao = lerOpcao(scanner);

        switch (opcao) {
            case 1 -> cadastrarCliente(clienteService, scanner);
            case 2 -> consultarCliente(clienteService, scanner);
            case 3 -> alterarCliente(clienteService, scanner);
            case 4 -> excluirCliente(clienteService, scanner);
            case 5 -> listarClientes(clienteService);
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void listarClientes(ClienteService clienteService) {
        clienteService.listarTodosClientes().forEach(System.out::println);
    }

    private static void cadastrarCliente(ClienteService clienteService, Scanner scanner) {
        Cliente cliente = criarCliente(scanner);
        clienteService.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso.");
    }

    private static Cliente criarCliente(Scanner scanner) {
        System.out.print("Informe o nome do cliente: ");
        String nome = scanner.next();
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.next();
        System.out.print("Informe o telefone do cliente: ");
        String telefone = scanner.next();
        System.out.print("Informe a cidade do cliente: ");
        String cidade = scanner.next();
        System.out.print("Informe o endereço do cliente: ");
        scanner.nextLine(); // Limpar o buffer
        String endereco = scanner.nextLine();
        System.out.print("Informe o estado do cliente: ");
        String estado = scanner.next();

        return new Cliente(null, nome, cpf, cidade, endereco, estado, telefone);
    }

    private static void consultarCliente(ClienteService clienteService, Scanner scanner) {
        System.out.print("Informe o CPF: ");
        String cpfConsulta = scanner.next();
        Optional<Cliente> clienteOptional = clienteService.consultarPorCpf(cpfConsulta);

        if (clienteOptional.isPresent()) {
            System.out.println("Cliente encontrado: " + clienteOptional.get());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void alterarCliente(ClienteService clienteService, Scanner scanner) {
        System.out.print("Informe o CPF do cliente que deseja alterar: ");
        String cpf = scanner.next();
        Optional<Cliente> clienteOptional = clienteService.consultarPorCpf(cpf);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            System.out.println("Alterando informações para o cliente: " + cliente.getNome());
            cliente = preencherDadosCliente(scanner, cliente);
            clienteService.alterarCliente(cliente);
            System.out.println("Cliente alterado com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static Cliente preencherDadosCliente(Scanner scanner, Cliente cliente) {
        System.out.print("Novo nome: ");
        cliente.setNome(scanner.next());

        System.out.print("Novo telefone: ");
        cliente.setTelefone(scanner.next());

        System.out.print("Nova cidade: ");
        cliente.setCidade(scanner.next());

        System.out.print("Novo endereço: ");
        scanner.nextLine(); // Limpar o buffer
        cliente.setEndereco(scanner.nextLine());

        System.out.print("Novo estado: ");
        cliente.setEstado(scanner.next());

        return cliente;
    }

    private static void excluirCliente(ClienteService clienteService, Scanner scanner) {
        System.out.print("Informe o CPF do cliente que deseja excluir: ");
        String cpf = scanner.next();
        clienteService.excluirCliente(cpf);
        System.out.println("Cliente excluído com sucesso.");
    }

    private static void gerenciarProduto(ProdutoService produtoService, Scanner scanner) {
        System.out.println("Produto: 1. Cadastrar 2. Consultar 3. Alterar 4. Excluir 5. Listar Todos");
        int opcao = lerOpcao(scanner);

        switch (opcao) {
            case 1 -> cadastrarProduto(produtoService, scanner);
            case 2 -> consultarProduto(produtoService, scanner);
            case 3 -> alterarProduto(produtoService, scanner);
            case 4 -> excluirProduto(produtoService, scanner);
            case 5 -> listarProdutos(produtoService);
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void listarProdutos(ProdutoService produtoService) {
        produtoService.listarTodosProdutos().forEach(System.out::println);
    }

    private static void cadastrarProduto(ProdutoService produtoService, Scanner scanner) {
        Produto produto = criarProduto(scanner);
        produtoService.cadastrarProduto(produto);
        System.out.println("Produto cadastrado com sucesso.");
    }

    private static Produto criarProduto(Scanner scanner) {
        System.out.print("Informe a descrição do produto: ");
        String descricao = scanner.next();
        System.out.print("Informe o valor unitário do produto: ");
        double valorUnitario = scanner.nextDouble();
        System.out.print("Informe o código do produto: ");
        String codigo = scanner.next();
        System.out.print("Informe a quantidade disponível: ");
        int quantidade = scanner.nextInt();

        return new Produto(descricao, valorUnitario, codigo, quantidade);
    }

    private static void consultarProduto(ProdutoService produtoService, Scanner scanner) {
        System.out.print("Informe o código do produto: ");
        String codigo = scanner.next();
        Optional<Produto> produtoOptional = produtoService.consultarPorCodigo(codigo);

        if (produtoOptional.isPresent()) {
            System.out.println("Produto encontrado: " + produtoOptional.get());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void alterarProduto(ProdutoService produtoService, Scanner scanner) {
        System.out.print("Informe o código do produto que deseja alterar: ");
        String codigo = scanner.next();
        Optional<Produto> produtoOptional = produtoService.consultarPorCodigo(codigo);

        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            System.out.println("Alterando informações para o produto: " + produto.getDescricao());
            produto = preencherDadosProduto(scanner, produto);
            produtoService.alterarProduto(produto);
            System.out.println("Produto alterado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static Produto preencherDadosProduto(Scanner scanner, Produto produto) {
        System.out.print("Nova descrição: ");
        produto.setDescricao(scanner.next());

        System.out.print("Novo valor unitário: ");
        produto.setValorUnitario(scanner.nextDouble());

        System.out.print("Novo código: ");
        produto.setCodigoProduto(scanner.next());

        System.out.print("Novo estoque: ");
        produto.setEstoque(scanner.nextInt());

        return produto;
    }

    private static void excluirProduto(ProdutoService produtoService, Scanner scanner) {
        System.out.print("Informe o código do produto que deseja excluir: ");
        String codigo = scanner.next();
        produtoService.excluirProduto(codigo);
        System.out.println("Produto excluído com sucesso.");
    }

    private static void gerenciarVenda(VendaService vendaService, ClienteService clienteService, ProdutoService produtoService, Scanner scanner) {
        System.out.print("Informe o número da nota fiscal: ");
        int numeroNota = scanner.nextInt();
        System.out.print("Informe o CPF do cliente: ");
        String cpfCliente = scanner.next();
        Optional<Cliente> clienteOptional = clienteService.consultarPorCpf(cpfCliente);

        if (clienteOptional.isPresent()) {
            Venda venda = new Venda(numeroNota, clienteOptional.get());

            while (true) {
                System.out.println("Adicionar produto à venda? (1. Sim / 2. Não)");
                int opcao = lerOpcao(scanner);
                if (opcao == 1) {
                    adicionarProdutoAVenda(produtoService, venda, scanner);
                } else {
                    break;
                }
            }

            vendaService.cadastrarVenda(venda);
            System.out.println("Venda realizada com sucesso!");
            System.out.println("Nota Fiscal:\n" + venda.gerarNotaFiscal());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void adicionarProdutoAVenda(ProdutoService produtoService, Venda venda, Scanner scanner) {
        System.out.print("Informe o código do produto: ");
        String codigoProduto = scanner.next();
        Optional<Produto> produtoOptional = produtoService.consultarPorCodigo(codigoProduto);

        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            System.out.print("Informe a quantidade: ");
            int quantidade = scanner.nextInt();

            if (quantidade <= produto.getEstoque()) {
                venda.adicionarProduto(produto, quantidade); // ajuste aqui para adicionar corretamente o produto na venda
                System.out.println("Produto adicionado à venda.");
            } else {
                System.out.println("Quantidade insuficiente em estoque.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
