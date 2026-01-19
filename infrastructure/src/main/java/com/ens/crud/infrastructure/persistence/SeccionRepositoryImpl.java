package com.ens.crud.infrastructure.persistence;

import com.ens.crud.domain.entity.Seccion;
import com.ens.crud.domain.repository.SeccionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SeccionRepositoryImpl implements SeccionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Seccion save(Seccion seccion) {
        if (seccion.getId() == null) {
            entityManager.persist(seccion);
            return seccion;
        } else {
            return entityManager.merge(seccion);
        }
    }

    @Override
    public Optional<Seccion> findById(Long id) {
        Seccion seccion = entityManager.find(Seccion.class, id);
        return Optional.ofNullable(seccion);
    }

    @Override
    public List<Seccion> findAll() {
        return entityManager.createQuery("SELECT s FROM Seccion s", Seccion.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Seccion seccion = entityManager.find(Seccion.class, id);
        if (seccion != null) {
            entityManager.remove(seccion);
        }
    }

    @Override
    public List<Seccion> findByAplicacionId(Long aplicacionId) {
        return entityManager.createQuery(
                "SELECT s FROM Seccion s WHERE s.aplicacion.id = :aplicacionId", Seccion.class)
                .setParameter("aplicacionId", aplicacionId)
                .getResultList();
    }
}
