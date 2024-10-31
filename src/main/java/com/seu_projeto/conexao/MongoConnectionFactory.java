package com.seu_projeto.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnectionFactory {
    private static final String URI = "mongodb://localhost:27017"; // URL do MongoDB
    private static final String DB_NAME = "testdb"; // Nome do seu banco de dados

    public static MongoDatabase getDatabase() {
        MongoClient mongoClient = MongoClients.create(URI);
        return mongoClient.getDatabase(DB_NAME);
    }
}
