package com.ens.crud.domain.repository;

import com.ens.crud.domain.entity.Autoriz;
import java.util.List;
import java.util.Optional;

public interface AutorizRepository {
    Autoriz save(Autoriz autoriz);
    Optional<Autoriz> findById(Long id);
    List<Autoriz> findAll();
    void delete(Long id);
    List<Autoriz> findByPerfilId(Long perfilId);
    List<Autoriz> findBySeccionId(Long seccionId);
}
