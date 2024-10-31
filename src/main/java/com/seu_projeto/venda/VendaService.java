package com.seu_projeto.venda;

import com.seu_projeto.venda.dao.IVendaDAO;

import java.util.List;
import java.util.Optional;

public class VendaService {

    private final IVendaDAO vendaDAO;

    // Construtor que aceita um objeto IVendaDAO para injeção de dependência
    public VendaService(IVendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    // Método para cadastrar uma nova venda
    public void cadastrarVenda(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("Venda não pode ser nula.");
        }
        vendaDAO.registrar(venda);
    }

    // Método para buscar uma venda por número da nota fiscal
    public Optional<Venda> buscarPorNumero(String numeroNotaFiscal) {
        if (numeroNotaFiscal == null || numeroNotaFiscal.isEmpty()) {
            throw new IllegalArgumentException("Número da nota fiscal não pode ser nulo ou vazio.");
        }
        return vendaDAO.buscarPorNumero(numeroNotaFiscal);
    }

    // Método para listar todas as vendas
    public List<Venda> listarTodasVendas() {
        return vendaDAO.listarTodas();
    }
}
