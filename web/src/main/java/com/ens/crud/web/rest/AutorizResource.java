package com.ens.crud.web.rest;

import com.ens.crud.application.usecase.AutorizUseCase;
import com.ens.crud.domain.entity.Autoriz;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/autorizaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutorizResource {

    @Inject
    private AutorizUseCase autorizUseCase;

    @GET
    public Response getAllAutorizaciones() {
        List<Autoriz> autorizaciones = autorizUseCase.getAllAutorizaciones();
        return Response.ok(autorizaciones).build();
    }

    @GET
    @Path("/{id}")
    public Response getAutorizById(@PathParam("id") Long id) {
        return autorizUseCase.getAutorizById(id)
                .map(autoriz -> Response.ok(autoriz).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/perfil/{perfilId}")
    public Response getAutorizacionesByPerfil(@PathParam("perfilId") Long perfilId) {
        List<Autoriz> autorizaciones = autorizUseCase.getAutorizacionesByPerfil(perfilId);
        return Response.ok(autorizaciones).build();
    }

    @GET
    @Path("/seccion/{seccionId}")
    public Response getAutorizacionesBySeccion(@PathParam("seccionId") Long seccionId) {
        List<Autoriz> autorizaciones = autorizUseCase.getAutorizacionesBySeccion(seccionId);
        return Response.ok(autorizaciones).build();
    }

    @POST
    public Response createAutoriz(Autoriz autoriz) {
        try {
            Autoriz created = autorizUseCase.createAutoriz(autoriz);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAutoriz(@PathParam("id") Long id, Autoriz autoriz) {
        try {
            Autoriz updated = autorizUseCase.updateAutoriz(id, autoriz);
            return Response.ok(updated).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAutoriz(@PathParam("id") Long id) {
        try {
            autorizUseCase.deleteAutoriz(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
