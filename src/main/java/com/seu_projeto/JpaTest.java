package com.seu_projeto;

import com.seu_projeto.cliente.Cliente;
import com.seu_projeto.produto.Produto;
import com.seu_projeto.venda.ItemVenda;
import com.seu_projeto.venda.Venda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setNome("João Silva");
            cliente.setCpf("12345678970");
            em.persist(cliente);

            Produto produto = new Produto();
            produto.setDescricao("Caneta Azul");
            produto.setValorUnitario(1.50);
            produto.setCodigoProduto("CAZ012");
            produto.setEstoque(100);
            em.persist(produto);

            Venda venda = new Venda(cliente);
            em.persist(venda);

            ItemVenda itemVenda = new ItemVenda(venda, produto, 10); // Cria o item de venda
            venda.adicionarItem(itemVenda); // Adiciona o item à venda
            em.persist(itemVenda);

            em.getTransaction().commit();
            System.out.println("Persistência concluída com sucesso!");

        } finally {
            em.close();
            emf.close();
        }
    }
}
