package endpoints;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.AuthModel;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BunkerEndpoint {
    private static final String baseUri = "https://virtserver.swaggerhub.com/roga88/bunker/1.0.0";
    private static final String basePath = "/login";
    private RequestSpecification rSpec;

    @BeforeMethod
    public void rSpec() {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON);
        rSpec = reqBuilder.build();
    }

    public Response auth(AuthModel model) {
        return given()
                .spec(rSpec)
                .body(model)
                .when()
                .post();
    }

    public Response authCustomBody(String body) {
        return given()
                .spec(rSpec)
                .body(body)
                .when()
                .post();
    }


}
