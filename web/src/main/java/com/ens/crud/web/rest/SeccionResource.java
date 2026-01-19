package com.ens.crud.web.rest;

import com.ens.crud.application.usecase.SeccionUseCase;
import com.ens.crud.domain.entity.Seccion;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/secciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeccionResource {

    @Inject
    private SeccionUseCase seccionUseCase;

    @GET
    public Response getAllSecciones() {
        List<Seccion> secciones = seccionUseCase.getAllSecciones();
        return Response.ok(secciones).build();
    }

    @GET
    @Path("/{id}")
    public Response getSeccionById(@PathParam("id") Long id) {
        return seccionUseCase.getSeccionById(id)
                .map(seccion -> Response.ok(seccion).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/aplicacion/{aplicacionId}")
    public Response getSeccionesByAplicacion(@PathParam("aplicacionId") Long aplicacionId) {
        List<Seccion> secciones = seccionUseCase.getSeccionesByAplicacion(aplicacionId);
        return Response.ok(secciones).build();
    }

    @POST
    public Response createSeccion(Seccion seccion) {
        try {
            Seccion created = seccionUseCase.createSeccion(seccion);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateSeccion(@PathParam("id") Long id, Seccion seccion) {
        try {
            Seccion updated = seccionUseCase.updateSeccion(id, seccion);
            return Response.ok(updated).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSeccion(@PathParam("id") Long id) {
        try {
            seccionUseCase.deleteSeccion(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
