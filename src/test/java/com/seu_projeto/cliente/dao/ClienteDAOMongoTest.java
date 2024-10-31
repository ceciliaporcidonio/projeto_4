import com.seu_projeto.cliente.Cliente;
import com.seu_projeto.cliente.dao.ClienteDAOMongo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteDAOMongoTest {
    private ClienteDAOMongo clienteDAO;

    @BeforeEach
    public void setUp() {
        clienteDAO = new ClienteDAOMongo();
    }

    @Test
    public void testCadastrarEConsultarCliente() {
        Cliente cliente = new Cliente("Ana", "123.456.789-00", "99999-9999", "São Paulo", "Rua A", "SP");
        clienteDAO.cadastrar(cliente);

        Cliente clienteConsultado = clienteDAO.consultarPorCpf("123.456.789-00");
        Assertions.assertNotNull(clienteConsultado);
        Assertions.assertEquals("Ana", clienteConsultado.getNome());
    }
}
