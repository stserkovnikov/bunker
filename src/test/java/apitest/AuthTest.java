package apitest;

import endpoints.BunkerEndpoint;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.AuthModel;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.File;

public class AuthTest extends BunkerEndpoint {
    @Test
    public void positiveTest() {
        Response response = auth(generateAuth("foo", "bar"));
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchema(getAuthPassedSchema()));
    }

    @Test
    public void noBodyRequestTest() {
        Response response = authCustomBody("");
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(JsonSchemaValidator.matchesJsonSchema(getAuthErrorSchema()));
    }

    @Test
    public void wrongFormatTest() {
        Response response = authCustomBody("foo=bar");
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(JsonSchemaValidator.matchesJsonSchema(getAuthErrorSchema()));
    }

    @Test
    public void noLoginTest() {
        AuthModel authModel = new AuthModel();
        authModel.setPassword("password");
        Response response = auth(authModel);
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(JsonSchemaValidator.matchesJsonSchema(getAuthErrorSchema()));
    }

    @Test
    public void noPasswordTest() {
        AuthModel authModel = new AuthModel();
        authModel.setLogin("login");
        Response response = auth(authModel);
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(JsonSchemaValidator.matchesJsonSchema(getAuthErrorSchema()));
    }

    private AuthModel generateAuth(String login, String password) {
        AuthModel authModel = new AuthModel();
        return authModel.setLogin(login).setPassword(password);
    }

    private File getAuthPassedSchema() {
        return new File("src/test/resources/SecurityToken.schema.json");
    }

    private File getAuthErrorSchema() {
        return new File("src/test/resources/AuthError.schema.json");
    }

}
