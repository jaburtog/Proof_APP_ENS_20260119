package com.ens.crud.domain.repository;

import com.ens.crud.domain.entity.Seccion;
import java.util.List;
import java.util.Optional;

public interface SeccionRepository {
    Seccion save(Seccion seccion);
    Optional<Seccion> findById(Long id);
    List<Seccion> findAll();
    void delete(Long id);
    List<Seccion> findByAplicacionId(Long aplicacionId);
}
