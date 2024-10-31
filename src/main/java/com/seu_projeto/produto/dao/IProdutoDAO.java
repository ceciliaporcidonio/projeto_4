package com.seu_projeto.produto.dao;

import com.seu_projeto.produto.Produto;

import java.util.List;

public interface IProdutoDAO {
    void cadastrar(Produto produto);
    Produto buscarPorDescricao(String descricao);
    Produto consultarPorCodigo(String codigoProduto); // Adicionado
    void alterar(Produto produto);
    void excluir(String codigoProduto);
    List<Produto> listarTodos();
}
