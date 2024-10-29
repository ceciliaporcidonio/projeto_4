package com.seu_projeto.venda.dao;

import com.seu_projeto.venda.Venda;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class VendaDAOPostgres implements IVendaDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void registrar(Venda venda) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Venda> buscarPorNumero(String numeroNotaFiscal) {
        EntityManager em = getEntityManager();
        try {
            Venda venda = em.find(Venda.class, Integer.parseInt(numeroNotaFiscal));
            return Optional.ofNullable(venda);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Venda> listarTodas() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("from Venda", Venda.class).getResultList();
        } finally {
            em.close();
        }
    }
    @Override
    public void excluir(String numeroNotaFiscal) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Venda venda = em.find(Venda.class, Integer.parseInt(numeroNotaFiscal));
            if (venda != null) {
                em.remove(venda);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
