package com.seu_projeto.venda;

import com.seu_projeto.venda.dao.IVendaDAO;
import com.seu_projeto.produto.Produto;
import com.seu_projeto.cliente.Cliente;

import java.util.List;
import java.util.Optional;

public class VendaService {

    private final IVendaDAO vendaDAO;

    public VendaService(IVendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    // Atualizado para aceitar um objeto Venda
    public void cadastrarVenda(Venda venda) {
        vendaDAO.registrar(venda);
    }

    public Optional<Venda> buscarPorNumero(String numeroNotaFiscal) {
        return vendaDAO.buscarPorNumero(numeroNotaFiscal);
    }

    public List<Venda> listarTodasVendas() {
        return vendaDAO.listarTodas();
    }
}
