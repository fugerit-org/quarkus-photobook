package test.org.fugerit.java.demo.quarkus.photobook.rest;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.ws.rs.core.Response;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.demo.quarkus.photobook.rest.RestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
@TestProfile(MongoContainerProfile.class)
class PhotobookRestTest {

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

    @Test
    void testImageOk() {
        given()
                .when()
                .get( "/api/photobook/view/download/springio23_1000.jpg" )
                .then()
                .statusCode(200);
    }

    @Test
    void testRestHelper() {
        Response res = RestHelper.defaultHandle( () -> {
            if ( Boolean.TRUE.booleanValue() ) {
                throw new ConfigRuntimeException( "scenario exception" );
            }
            return null;
        } );
        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), res.getStatus());
    }

}
