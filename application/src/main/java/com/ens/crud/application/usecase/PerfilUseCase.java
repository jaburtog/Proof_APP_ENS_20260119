package com.ens.crud.application.usecase;

import com.ens.crud.domain.entity.Perfil;
import com.ens.crud.domain.repository.PerfilRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PerfilUseCase {

    @Inject
    private PerfilRepository perfilRepository;

    public Perfil createPerfil(Perfil perfil) {
        if (perfilRepository.findByNombre(perfil.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Perfil name already exists");
        }
        return perfilRepository.save(perfil);
    }

    public Optional<Perfil> getPerfilById(Long id) {
        return perfilRepository.findById(id);
    }

    public List<Perfil> getAllPerfiles() {
        return perfilRepository.findAll();
    }

    public Perfil updatePerfil(Long id, Perfil perfil) {
        Perfil existing = perfilRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Perfil not found"));
        
        existing.setNombre(perfil.getNombre());
        existing.setDescripcion(perfil.getDescripcion());
        existing.setActivo(perfil.getActivo());
        
        return perfilRepository.save(existing);
    }

    public void deletePerfil(Long id) {
        if (!perfilRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Perfil not found");
        }
        perfilRepository.delete(id);
    }
}
