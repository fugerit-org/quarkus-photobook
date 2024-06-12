package test.org.fugerit.java.demo.quarkus.photobook.rest;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class MetaRestTest {

    @Test
    void testMetaOk() {
        given()
                .when()
                .get( "/api/meta/version" )
                .then()
                .statusCode(200);
    }

}
