import io.restassured.http.Method;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BaseApi {

    static ApiConfig config = new ApiConfig();

    public static Response makeAGetRequestWith(ApiConfig config) {
        Response response = given()
                .log()
                .all()
                .baseUri(config.getBaseURI())
                .basePath(config.getEndPoint())
                .port(config.getPortNumber())
                .when()
                .request(Method.GET);

        return response;
    }

    public static Response makeAPostRequestWith(ApiConfig config) throws IOException {
        Response response = given()
                .log()
                .all()
                .baseUri(config.getBaseURI())
                .basePath(config.getEndPoint())
                .port(config.getPortNumber())
                .body(FileUtils.readAllContentFromFile(System.getProperty("user.dir")
                        + config.getBody()))
                .when()
                .request(Method.POST);

        return response;
    }

    public static Response makeAPostRequestWithoutPort(ApiConfig config) throws IOException {
        Response response = given()
                .log()
                .all()
                .baseUri(config.getBaseURI())
                .basePath(config.getEndPoint())
                .body(FileUtils.readAllContentFromFile(System.getProperty("user.dir")
                        + config.getBody()))
                .when()
                .request(Method.POST);

        return response;
    }

    public void makeAPutRequestWith() {

    }

    public void makeAPatchRequestWith() {

    }
}
