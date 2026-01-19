package com.ens.crud.infrastructure.persistence;

import com.ens.crud.domain.entity.Usuario;
import com.ens.crud.domain.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            entityManager.persist(usuario);
            return usuario;
        } else {
            return entityManager.merge(usuario);
        }
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        return Optional.ofNullable(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        List<Usuario> usuarios = entityManager.createQuery(
                "SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username)
                .getResultList();
        return usuarios.isEmpty() ? Optional.empty() : Optional.of(usuarios.get(0));
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        List<Usuario> usuarios = entityManager.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList();
        return usuarios.isEmpty() ? Optional.empty() : Optional.of(usuarios.get(0));
    }
}
