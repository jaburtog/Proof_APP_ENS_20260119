package com.ens.crud.web.rest;

import com.ens.crud.application.usecase.AplicacionUseCase;
import com.ens.crud.domain.entity.Aplicacion;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/aplicaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AplicacionResource {

    @Inject
    private AplicacionUseCase aplicacionUseCase;

    @GET
    public Response getAllAplicaciones() {
        List<Aplicacion> aplicaciones = aplicacionUseCase.getAllAplicaciones();
        return Response.ok(aplicaciones).build();
    }

    @GET
    @Path("/{id}")
    public Response getAplicacionById(@PathParam("id") Long id) {
        return aplicacionUseCase.getAplicacionById(id)
                .map(aplicacion -> Response.ok(aplicacion).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createAplicacion(Aplicacion aplicacion) {
        try {
            Aplicacion created = aplicacionUseCase.createAplicacion(aplicacion);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAplicacion(@PathParam("id") Long id, Aplicacion aplicacion) {
        try {
            Aplicacion updated = aplicacionUseCase.updateAplicacion(id, aplicacion);
            return Response.ok(updated).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAplicacion(@PathParam("id") Long id) {
        try {
            aplicacionUseCase.deleteAplicacion(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
