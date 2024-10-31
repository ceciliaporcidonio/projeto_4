import com.seu_projeto.cliente.Cliente;
import com.seu_projeto.cliente.ClienteService;
import com.seu_projeto.cliente.dao.IClienteDAO;
import com.seu_projeto.produto.Produto;
import com.seu_projeto.produto.ProdutoService;
import com.seu_projeto.produto.dao.IProdutoDAO;
import com.seu_projeto.venda.Venda;
import com.seu_projeto.venda.VendaService;
import com.seu_projeto.venda.dao.IVendaDAO;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class IntegracaoTest {

    private IClienteDAO clienteDAO;
    private IProdutoDAO produtoDAO;
    private IVendaDAO vendaDAO;
    private ClienteService clienteService;
    private ProdutoService produtoService;
    private VendaService vendaService;

    @BeforeEach
    public void setup() {
        clienteDAO = mock(IClienteDAO.class);
        produtoDAO = mock(IProdutoDAO.class);
        vendaDAO = mock(IVendaDAO.class);

        clienteService = new ClienteService(clienteDAO);
        produtoService = new ProdutoService(produtoDAO);
        vendaService = new VendaService(vendaDAO);
    }

    @Test
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente("Ana", "123.456.789-00", "99999-9999", "São Paulo", "Rua A", "SP");
        clienteService.cadastrarCliente(cliente);
        verify(clienteDAO, times(1)).cadastrar(cliente);
    }

    @Test
    public void testConsultarCliente() {
        String cpf = "123.456.789-00";
        Cliente cliente = new Cliente("Ana", cpf, "99999-9999", "São Paulo", "Rua A", "SP");
        when(clienteDAO.consultar(cpf)).thenReturn(Optional.of(cliente));

        Optional<Cliente> clienteConsultado = clienteService.consultarPorCpf(cpf);
        Assertions.assertTrue(clienteConsultado.isPresent(), "Cliente deve estar presente.");
        Assertions.assertEquals(cliente, clienteConsultado.get());
    }

    @Test
    public void testConsultarProduto() {
        String descricao = "Produto A";
        Produto produto = new Produto(descricao, 10.0, "001", 50);

        when(produtoDAO.buscarPorDescricao(descricao)).thenReturn(Optional.of(produto));

        Optional<Produto> produtoConsultado = produtoService.buscarPorDescricao(descricao);
        Assertions.assertTrue(produtoConsultado.isPresent(), "Produto deve estar presente.");
        Assertions.assertEquals(produto, produtoConsultado.get());
    }

    @Test
    public void testCadastrarVenda() {
        Cliente cliente = new Cliente("Ana", "123.456.789-00", "99999-9999", "São Paulo", "Rua A", "SP");
        Venda venda = new Venda(cliente);
        
        vendaService.cadastrarVenda(venda);
        
        verify(vendaDAO, times(1)).cadastrar(venda);
        Assertions.assertNotNull(venda.getCliente(), "O cliente da venda não deve ser nulo.");
    }
}
