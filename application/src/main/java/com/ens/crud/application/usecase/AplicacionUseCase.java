package com.ens.crud.application.usecase;

import com.ens.crud.domain.entity.Aplicacion;
import com.ens.crud.domain.repository.AplicacionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AplicacionUseCase {

    @Inject
    private AplicacionRepository aplicacionRepository;

    public Aplicacion createAplicacion(Aplicacion aplicacion) {
        if (aplicacionRepository.findByNombre(aplicacion.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Aplicacion name already exists");
        }
        return aplicacionRepository.save(aplicacion);
    }

    public Optional<Aplicacion> getAplicacionById(Long id) {
        return aplicacionRepository.findById(id);
    }

    public List<Aplicacion> getAllAplicaciones() {
        return aplicacionRepository.findAll();
    }

    public Aplicacion updateAplicacion(Long id, Aplicacion aplicacion) {
        Aplicacion existing = aplicacionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aplicacion not found"));
        
        existing.setNombre(aplicacion.getNombre());
        existing.setDescripcion(aplicacion.getDescripcion());
        existing.setUrl(aplicacion.getUrl());
        existing.setActivo(aplicacion.getActivo());
        
        return aplicacionRepository.save(existing);
    }

    public void deleteAplicacion(Long id) {
        if (!aplicacionRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Aplicacion not found");
        }
        aplicacionRepository.delete(id);
    }
}
