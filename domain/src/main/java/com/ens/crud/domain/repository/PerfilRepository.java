package com.ens.crud.domain.repository;

import com.ens.crud.domain.entity.Perfil;
import java.util.List;
import java.util.Optional;

public interface PerfilRepository {
    Perfil save(Perfil perfil);
    Optional<Perfil> findById(Long id);
    List<Perfil> findAll();
    void delete(Long id);
    Optional<Perfil> findByNombre(String nombre);
}
