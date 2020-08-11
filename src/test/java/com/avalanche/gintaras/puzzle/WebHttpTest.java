package com.avalanche.gintaras.puzzle;

import com.avalanche.gintaras.puzzle.model.Move;
import com.avalanche.gintaras.puzzle.model.Position;
import com.avalanche.gintaras.puzzle.model.Tile;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *  Tests rest endpoint.
 *
 * @created 04/08/2020 - 14:03
 * @author gintaras
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebHttpTest {

    private static final int VALUE_15 = 15;
    private static final int POSITION_1 = 1;
    private static final int POSITION_2 = 2;

    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    public void shouldReturnJsonObjectOnStart() throws Exception {
        String result = given().contentType("application/json")
                .with().param("players", 1)
                .when().get("http://localhost:" + port + "/puzzle/start")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract().asString();
        assertThat(result).contains("player1");

    }

    @Test
    @Order(2)
    public void shouldReturnJsonObjectOnMove() throws Exception {
        Map<String, Move> bodyMap = new HashMap<>();
        bodyMap.put("player1", new Move(new Tile(VALUE_15, new Position(POSITION_1,POSITION_1)),
                        new Position(POSITION_2,POSITION_2)));
        String result = given().contentType("application/json")
                .with().body(bodyMap)
                .when().post("http://localhost:" + port + "/puzzle/move")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(ContentType.JSON)
                .extract().asString();
        assertThat(result).contains("player1");

    }

    @Test
    @Order(3)
    public void shouldReturnJsonObjectOnCounter() throws Exception {
        String result = given()
                .when().get("http://localhost:" + port + "/puzzle/counter")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract().asString();
        assertThat(result).contains("player1");
    }

}
