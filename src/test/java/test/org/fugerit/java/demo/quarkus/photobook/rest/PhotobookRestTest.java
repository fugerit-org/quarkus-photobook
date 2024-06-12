package test.org.fugerit.java.demo.quarkus.photobook.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.MountableFile;

import java.io.File;

import static io.restassured.RestAssured.given;

@QuarkusTest
class PhotobookRestTest {

    final static GenericContainer mongoDBContainer = new GenericContainer( "mongo:8.0.0-rc7" )
            .withCopyToContainer(MountableFile.forHostPath( new File( "src/test/resources/mongo-db/mongo-init.js" ).getPath() ), "/docker-entrypoint-initdb.d/mongo-init.js" )
            .withExposedPorts( 27017 );

    @Test
    void testListOk() {
        given()
                .when()
                .get( "/api/photobook/view/list" )
                .then()
                .statusCode(200);
    }

    @Test
    void testAlbumOk() {
        given()
                .when()
                .get( "/api/photobook/view/images/springio23" )
                .then()
                .statusCode(200);
    }

}
