package com.ens.crud.infrastructure.persistence;

import com.ens.crud.domain.entity.Perfil;
import com.ens.crud.domain.repository.PerfilRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PerfilRepositoryImpl implements PerfilRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Perfil save(Perfil perfil) {
        if (perfil.getId() == null) {
            entityManager.persist(perfil);
            return perfil;
        } else {
            return entityManager.merge(perfil);
        }
    }

    @Override
    public Optional<Perfil> findById(Long id) {
        Perfil perfil = entityManager.find(Perfil.class, id);
        return Optional.ofNullable(perfil);
    }

    @Override
    public List<Perfil> findAll() {
        return entityManager.createQuery("SELECT p FROM Perfil p", Perfil.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Perfil perfil = entityManager.find(Perfil.class, id);
        if (perfil != null) {
            entityManager.remove(perfil);
        }
    }

    @Override
    public Optional<Perfil> findByNombre(String nombre) {
        List<Perfil> perfiles = entityManager.createQuery(
                "SELECT p FROM Perfil p WHERE p.nombre = :nombre", Perfil.class)
                .setParameter("nombre", nombre)
                .getResultList();
        return perfiles.isEmpty() ? Optional.empty() : Optional.of(perfiles.get(0));
    }
}
