package org.fugerit.java.demo.quarkus.photobook.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.util.PropsIO;

import java.util.Properties;

@ApplicationScoped
@Slf4j
@Path("/api/meta")
public class MetaRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/version")
    public Response getVersion() {
        return RestHelper.defaultHandle( () -> {
            Properties buildProps = PropsIO.loadFromClassLoader( "build.properties" );
            log.info( "buildProps : {}", buildProps );
            return Response.ok().entity( buildProps ).build();
        } );
    }

}
