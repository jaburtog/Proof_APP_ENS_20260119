package com.ens.crud.web.rest;

import com.ens.crud.application.usecase.PerfilUseCase;
import com.ens.crud.domain.entity.Perfil;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/perfiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilResource {

    @Inject
    private PerfilUseCase perfilUseCase;

    @GET
    public Response getAllPerfiles() {
        List<Perfil> perfiles = perfilUseCase.getAllPerfiles();
        return Response.ok(perfiles).build();
    }

    @GET
    @Path("/{id}")
    public Response getPerfilById(@PathParam("id") Long id) {
        return perfilUseCase.getPerfilById(id)
                .map(perfil -> Response.ok(perfil).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createPerfil(Perfil perfil) {
        try {
            Perfil created = perfilUseCase.createPerfil(perfil);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePerfil(@PathParam("id") Long id, Perfil perfil) {
        try {
            Perfil updated = perfilUseCase.updatePerfil(id, perfil);
            return Response.ok(updated).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerfil(@PathParam("id") Long id) {
        try {
            perfilUseCase.deletePerfil(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
