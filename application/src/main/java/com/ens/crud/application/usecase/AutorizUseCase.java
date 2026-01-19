package com.ens.crud.application.usecase;

import com.ens.crud.domain.entity.Autoriz;
import com.ens.crud.domain.repository.AutorizRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AutorizUseCase {

    @Inject
    private AutorizRepository autorizRepository;

    public Autoriz createAutoriz(Autoriz autoriz) {
        if (autoriz.getPerfil() == null || autoriz.getSeccion() == null) {
            throw new IllegalArgumentException("Perfil and Seccion are required");
        }
        return autorizRepository.save(autoriz);
    }

    public Optional<Autoriz> getAutorizById(Long id) {
        return autorizRepository.findById(id);
    }

    public List<Autoriz> getAllAutorizaciones() {
        return autorizRepository.findAll();
    }

    public List<Autoriz> getAutorizacionesByPerfil(Long perfilId) {
        return autorizRepository.findByPerfilId(perfilId);
    }

    public List<Autoriz> getAutorizacionesBySeccion(Long seccionId) {
        return autorizRepository.findBySeccionId(seccionId);
    }

    public Autoriz updateAutoriz(Long id, Autoriz autoriz) {
        Autoriz existing = autorizRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Autoriz not found"));
        
        existing.setPerfil(autoriz.getPerfil());
        existing.setSeccion(autoriz.getSeccion());
        existing.setPuedeLeer(autoriz.getPuedeLeer());
        existing.setPuedeEscribir(autoriz.getPuedeEscribir());
        existing.setPuedeEliminar(autoriz.getPuedeEliminar());
        existing.setActivo(autoriz.getActivo());
        
        return autorizRepository.save(existing);
    }

    public void deleteAutoriz(Long id) {
        if (!autorizRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Autoriz not found");
        }
        autorizRepository.delete(id);
    }
}
