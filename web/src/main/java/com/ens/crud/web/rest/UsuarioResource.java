package com.ens.crud.web.rest;

import com.ens.crud.application.usecase.UsuarioUseCase;
import com.ens.crud.domain.entity.Perfil;
import com.ens.crud.domain.entity.Usuario;
import com.ens.crud.web.dto.UsuarioDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioUseCase usuarioUseCase;

    @GET
    public Response getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioUseCase.getAllUsuarios().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    public Response getUsuarioById(@PathParam("id") Long id) {
        return usuarioUseCase.getUsuarioById(id)
                .map(this::toDTO)
                .map(dto -> Response.ok(dto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createUsuario(UsuarioDTO dto) {
        try {
            Usuario usuario = toEntity(dto);
            Usuario created = usuarioUseCase.createUsuario(usuario);
            return Response.status(Response.Status.CREATED).entity(toDTO(created)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateUsuario(@PathParam("id") Long id, UsuarioDTO dto) {
        try {
            Usuario usuario = toEntity(dto);
            Usuario updated = usuarioUseCase.updateUsuario(id, usuario);
            return Response.ok(toDTO(updated)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUsuario(@PathParam("id") Long id) {
        try {
            usuarioUseCase.deleteUsuario(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setEmail(usuario.getEmail());
        dto.setActivo(usuario.getActivo());
        dto.setFechaCreacion(usuario.getFechaCreacion());
        dto.setFechaModificacion(usuario.getFechaModificacion());
        if (usuario.getPerfil() != null) {
            dto.setPerfilId(usuario.getPerfil().getId());
            dto.setPerfilNombre(usuario.getPerfil().getNombre());
        }
        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setActivo(dto.getActivo());
        if (dto.getPerfilId() != null) {
            Perfil perfil = new Perfil();
            perfil.setId(dto.getPerfilId());
            usuario.setPerfil(perfil);
        }
        return usuario;
    }
}
