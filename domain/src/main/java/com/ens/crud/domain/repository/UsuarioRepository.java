package com.ens.crud.domain.repository;

import com.ens.crud.domain.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll();
    void delete(Long id);
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
}
