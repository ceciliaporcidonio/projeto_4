package com.seu_projeto.cliente.dao;

import com.seu_projeto.cliente.Cliente;

public interface IClienteDAOMongo {
    void cadastrar(Cliente cliente);
    Cliente consultarPorCpf(String cpf);
    // Adicione outros métodos conforme necessário
}
