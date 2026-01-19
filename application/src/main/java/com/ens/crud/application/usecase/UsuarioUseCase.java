package com.ens.crud.application.usecase;

import com.ens.crud.domain.entity.Usuario;
import com.ens.crud.domain.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioUseCase {

    @Inject
    private UsuarioRepository usuarioRepository;

    public Usuario createUsuario(Usuario usuario) {
        // Validate unique constraints
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario existing = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
        
        // Update fields
        existing.setNombre(usuario.getNombre());
        existing.setApellido(usuario.getApellido());
        existing.setEmail(usuario.getEmail());
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            existing.setPassword(usuario.getPassword());
        }
        existing.setPerfil(usuario.getPerfil());
        existing.setActivo(usuario.getActivo());
        
        return usuarioRepository.save(existing);
    }

    public void deleteUsuario(Long id) {
        if (!usuarioRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Usuario not found");
        }
        usuarioRepository.delete(id);
    }
}
