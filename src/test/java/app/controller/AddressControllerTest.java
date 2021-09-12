package app.controller;

import app.template.AddressTemplates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {

    @LocalServerPort
    private int port;

    private static final String END_POINT = "/address";

    @Test
    void mustCreateAddress() {
        final var address = AddressTemplates.defaultBuilder().build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(address)
                .port(port)
                .post(END_POINT)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log()
                .ifError();
    }

    @Test
    @Sql("classpath:/data/test/INSERT_INTO_ADDRESS.sql")
    void mustUpdateAddress() {
        final var addressToUpdate = AddressTemplates
                .defaultBuilder()
                .id(1L)
                .city("Sao Jose dos Campos")
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(addressToUpdate)
                .port(port)
                .put(END_POINT + "/" + addressToUpdate.getId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("city", Matchers.equalTo(addressToUpdate.getCity()))
                .log()
                .all();

    }

}