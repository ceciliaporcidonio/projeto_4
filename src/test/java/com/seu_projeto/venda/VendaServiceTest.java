import com.seu_projeto.cliente.Cliente;
import com.seu_projeto.produto.Produto;
import com.seu_projeto.venda.ItemVenda;
import com.seu_projeto.venda.Venda;
import com.seu_projeto.venda.VendaService;
import com.seu_projeto.venda.dao.VendaDAOMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VendaServiceTest {

    private VendaService vendaService;

    @BeforeEach
    public void setUp() {
        vendaService = new VendaService(new VendaDAOMemoria());
    }

    @Test
    public void testRegistrarVenda() {
        Cliente cliente = new Cliente(1, "João", "12345678900", "São Paulo", "Rua A", "SP", "12345-6789");

        Produto produto1 = new Produto(1, "Produto A", 50.0, "001", 100);
        Produto produto2 = new Produto(2, "Produto B", 30.0, "002", 50);

        Map<Produto, Integer> produtos = new HashMap<>();
        produtos.put(produto1, 2);
        produtos.put(produto2, 1);

        Venda venda = new Venda(cliente);
        
        // Adiciona itens à venda
        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
            Produto produto = entry.getKey();
            Integer quantidade = entry.getValue();
            ItemVenda itemVenda = new ItemVenda(produto, quantidade);
            venda.adicionarItem(itemVenda); // Use o método para adicionar o item
        }

        vendaService.cadastrarVenda(venda);

        // Assegure-se de usar um identificador válido para buscar a venda
        assertTrue(vendaService.buscarPorNumero(venda.getNumeroNotaFiscal()).isPresent(), "Venda não registrada corretamente.");
    }
}
