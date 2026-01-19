package com.ens.crud.infrastructure.persistence;

import com.ens.crud.domain.entity.Aplicacion;
import com.ens.crud.domain.repository.AplicacionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AplicacionRepositoryImpl implements AplicacionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Aplicacion save(Aplicacion aplicacion) {
        if (aplicacion.getId() == null) {
            entityManager.persist(aplicacion);
            return aplicacion;
        } else {
            return entityManager.merge(aplicacion);
        }
    }

    @Override
    public Optional<Aplicacion> findById(Long id) {
        Aplicacion aplicacion = entityManager.find(Aplicacion.class, id);
        return Optional.ofNullable(aplicacion);
    }

    @Override
    public List<Aplicacion> findAll() {
        return entityManager.createQuery("SELECT a FROM Aplicacion a", Aplicacion.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Aplicacion aplicacion = entityManager.find(Aplicacion.class, id);
        if (aplicacion != null) {
            entityManager.remove(aplicacion);
        }
    }

    @Override
    public Optional<Aplicacion> findByNombre(String nombre) {
        List<Aplicacion> aplicaciones = entityManager.createQuery(
                "SELECT a FROM Aplicacion a WHERE a.nombre = :nombre", Aplicacion.class)
                .setParameter("nombre", nombre)
                .getResultList();
        return aplicaciones.isEmpty() ? Optional.empty() : Optional.of(aplicaciones.get(0));
    }
}
