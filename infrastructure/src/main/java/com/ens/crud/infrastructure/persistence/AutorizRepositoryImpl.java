package com.ens.crud.infrastructure.persistence;

import com.ens.crud.domain.entity.Autoriz;
import com.ens.crud.domain.repository.AutorizRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AutorizRepositoryImpl implements AutorizRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Autoriz save(Autoriz autoriz) {
        if (autoriz.getId() == null) {
            entityManager.persist(autoriz);
            return autoriz;
        } else {
            return entityManager.merge(autoriz);
        }
    }

    @Override
    public Optional<Autoriz> findById(Long id) {
        Autoriz autoriz = entityManager.find(Autoriz.class, id);
        return Optional.ofNullable(autoriz);
    }

    @Override
    public List<Autoriz> findAll() {
        return entityManager.createQuery("SELECT a FROM Autoriz a", Autoriz.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Autoriz autoriz = entityManager.find(Autoriz.class, id);
        if (autoriz != null) {
            entityManager.remove(autoriz);
        }
    }

    @Override
    public List<Autoriz> findByPerfilId(Long perfilId) {
        return entityManager.createQuery(
                "SELECT a FROM Autoriz a WHERE a.perfil.id = :perfilId", Autoriz.class)
                .setParameter("perfilId", perfilId)
                .getResultList();
    }

    @Override
    public List<Autoriz> findBySeccionId(Long seccionId) {
        return entityManager.createQuery(
                "SELECT a FROM Autoriz a WHERE a.seccion.id = :seccionId", Autoriz.class)
                .setParameter("seccionId", seccionId)
                .getResultList();
    }
}
