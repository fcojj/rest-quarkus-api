package tech.ada.web2.quarkus.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import tech.ada.web2.quarkus.dto.BancoRequestDto;
import tech.ada.web2.quarkus.dto.BancoResponseDto;
import tech.ada.web2.quarkus.service.BancoService;

import java.net.URI;
import java.util.List;

@Path("/v1/bancos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RequiredArgsConstructor
public class BancoResource {

    @Inject
    private BancoService bancoService;
    @Inject
    private Logger logger;

    @GET
    public List<BancoResponseDto> getBancos() {

        return bancoService.getBancos();
    }

    @POST
    public Response saveBanco(BancoRequestDto request) {
        logger.info("Salvando um novo banco ...");

        BancoResponseDto bancoResponseDto = bancoService.addBanco(request);

        logger.infof("Banco salvo %s com sucesso.", bancoResponseDto);

        URI location = URI.create("/v1/bancos/" + bancoResponseDto.id());
        return Response.created(location).entity(bancoResponseDto).build();
    }

    @GET
    @Path("/{id}")
    public BancoResponseDto getBanco(@PathParam("id") Long id) {

        return bancoService.getBanco(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBanco(@PathParam("id") Long id){
        bancoService.deleteBanco(id);

        return Response.noContent().build(); // 204 http status
    }

    @PUT
    @Path("/{id}")
    public Response updateBanco(@PathParam("id") Long id, BancoRequestDto request){

        return Response.ok(bancoService.updateBanco(id, request)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response partialUpdateBanco(@PathParam("id") Long id, BancoRequestDto request){

        return Response.ok(bancoService.partialUpdateBanco(id, request)).build();
    }
}
