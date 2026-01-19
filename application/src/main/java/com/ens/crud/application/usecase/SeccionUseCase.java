package com.ens.crud.application.usecase;

import com.ens.crud.domain.entity.Seccion;
import com.ens.crud.domain.repository.SeccionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SeccionUseCase {

    @Inject
    private SeccionRepository seccionRepository;

    public Seccion createSeccion(Seccion seccion) {
        if (seccion.getAplicacion() == null) {
            throw new IllegalArgumentException("Aplicacion is required");
        }
        return seccionRepository.save(seccion);
    }

    public Optional<Seccion> getSeccionById(Long id) {
        return seccionRepository.findById(id);
    }

    public List<Seccion> getAllSecciones() {
        return seccionRepository.findAll();
    }

    public List<Seccion> getSeccionesByAplicacion(Long aplicacionId) {
        return seccionRepository.findByAplicacionId(aplicacionId);
    }

    public Seccion updateSeccion(Long id, Seccion seccion) {
        Seccion existing = seccionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Seccion not found"));
        
        existing.setNombre(seccion.getNombre());
        existing.setDescripcion(seccion.getDescripcion());
        existing.setRuta(seccion.getRuta());
        existing.setAplicacion(seccion.getAplicacion());
        existing.setActivo(seccion.getActivo());
        
        return seccionRepository.save(existing);
    }

    public void deleteSeccion(Long id) {
        if (!seccionRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Seccion not found");
        }
        seccionRepository.delete(id);
    }
}
