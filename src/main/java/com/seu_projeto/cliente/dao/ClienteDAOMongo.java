package com.seu_projeto.cliente.dao;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.seu_projeto.cliente.Cliente;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAOMongo implements IClienteDAOMongo {
    private final MongoDatabase database;

    @Autowired
    public ClienteDAOMongo(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public void cadastrar(Cliente cliente) {
        MongoCollection<Document> collection = database.getCollection("clientes");
        Document doc = new Document("nome", cliente.getNome())
                .append("cpf", cliente.getCpf())
                .append("telefone", cliente.getTelefone())
                .append("cidade", cliente.getCidade())
                .append("endereco", cliente.getEndereco())
                .append("estado", cliente.getEstado());
        collection.insertOne(doc);
    }

    @Override
    public Cliente consultarPorCpf(String cpf) {
        MongoCollection<Document> collection = database.getCollection("clientes");
        Document doc = collection.find(Filters.eq("cpf", cpf)).first();
        if (doc != null) {
            return new Cliente(
                    doc.getString("nome"),
                    doc.getString("cpf"),
                    doc.getString("cidade"),
                    doc.getString("endereco"),
                    doc.getString("estado"),
                    doc.getString("telefone")
            );
        }
        return null;
    }
}
