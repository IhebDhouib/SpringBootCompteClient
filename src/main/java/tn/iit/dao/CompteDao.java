package tn.iit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tn.iit.entity.Compte;

@Repository
public class CompteDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Compte compte) {
        entityManager.persist(compte);
    }

    public void update(Compte compte) {
        entityManager.merge(compte);
    }

    public void delete(Compte compte) {
        entityManager.remove(compte);
    }

    public Compte findById(Integer rib) {
        return entityManager.find(Compte.class, rib);
    }

    public List<Compte> findAll() {
        // JPQL - Object-oriented SQL
        return entityManager.createQuery("select c from Compte c", Compte.class).getResultList();
    }

    public List<Compte> findByNomClient(String nomClient) {
        // JPQL - Object-oriented SQL
        return entityManager.createQuery("select c from Compte c where c.nomClient = :nomClient", Compte.class)
                .setParameter("nomClient", nomClient)
                .getResultList();
    }

    public List<Compte> findByNomClient2(String nomClient) {
        // Native SQL
        return entityManager.createNativeQuery("select * from t_compte c where c.client = :nomClient", Compte.class)
                .setParameter("nomClient", nomClient)
                .getResultList();
    }
}
