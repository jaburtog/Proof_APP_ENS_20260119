package com.ens.crud.domain.repository;

import com.ens.crud.domain.entity.Aplicacion;
import java.util.List;
import java.util.Optional;

public interface AplicacionRepository {
    Aplicacion save(Aplicacion aplicacion);
    Optional<Aplicacion> findById(Long id);
    List<Aplicacion> findAll();
    void delete(Long id);
    Optional<Aplicacion> findByNombre(String nombre);
}
