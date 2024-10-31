package com.seu_projeto.produto.dao;

import com.seu_projeto.produto.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProdutoDAOJPA implements IProdutoDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Produto consultarPorCodigo(String codigoProduto) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p WHERE p.codigoProduto = :codigoProduto", Produto.class);
            query.setParameter("codigoProduto", codigoProduto);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(String codigoProduto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Produto produto = consultarPorCodigo(codigoProduto);
            if (produto != null) {
                em.remove(em.contains(produto) ? produto : em.merge(produto));
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> listarTodos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void alterar(Produto produto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Implementação do método 'buscarPorDescricao'
    @Override
    public Produto buscarPorDescricao(String descricao) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p WHERE p.descricao = :descricao", Produto.class);
            query.setParameter("descricao", descricao);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
