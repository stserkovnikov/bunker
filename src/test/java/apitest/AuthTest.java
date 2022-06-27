package apitest;

import endpoints.BunkerEndpoint;
import io.restassured.response.Response;
import models.AuthModel;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class AuthTest extends BunkerEndpoint {
    @Test
    public void positiveTest() {
        Response response = auth(generateAuth("login","password"));
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void negativeTest() {
        Response response = authWrongPath();
        response.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void noLoginTest() {
        AuthModel authModel = new AuthModel();
        authModel.setPassword("password");
        Response response = auth(authModel);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST); // should not be OK?
    }

    @Test
    public void noPasswordTest() {
        AuthModel authModel = new AuthModel();
        authModel.setLogin("login");
        Response response = auth(authModel);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST); // should not be OK?
    }

    private AuthModel generateAuth(String login, String password) {
        AuthModel authModel = new AuthModel();
        return authModel.setLogin(login).setPassword(password);
    }
}
