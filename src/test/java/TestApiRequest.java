import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class TestApiRequest {

    @Test
    public void AddAPet(){

                given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                "  \"id\": 1,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"cat\"\n" +
                "  },\n" +
                "  \"name\": \"peluchin\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"peluchin\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}")
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", notNullValue());
        }

    @Test
    public void GetAPet(){
        given()
                .contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void UpdateAPet(){
        String nameUpdated= given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"CATEGORIA\"\n" +
                        "  },\n" +
                        "  \"name\": \"OREJONA\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"unavailable\"\n" +
                        "}")
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath().getString("name");
        assertThat(nameUpdated,equalTo("OREJONA"));
    }
}
