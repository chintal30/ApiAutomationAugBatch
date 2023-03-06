import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class SampleAPITests {

    String baseURI = "https://reqres.in";


    @Test
    public void sampleGetAPITest() {
        String baseURI = "https://reqres.in";
        String endPoint = "/api/users?page=2";
        int responseCode = 200;

        given()
                .baseUri(baseURI)
                .basePath(endPoint)
                .when()
                .request(Method.GET)
                .then()
                .statusCode(responseCode)
                .and()
                .assertThat()
                .body(containsString("Janet"))
                .and()
                .log().all();
    }

    @Test
    public void samplePostAPITest() {
        String basePath = "/api/users";
        given()
                .contentType(ContentType.JSON) // body type
                .baseUri(baseURI)
                .basePath(basePath)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .request(Method.POST)
                .then()
                .statusCode(201)
                .body(containsString("morpheus"))
                .and()
                .log().all();
    }

    @Test
    public void samplePostAPIWithBodyFromFileTest() throws Exception {
        String basePath = "/api/users";
        given()
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .basePath(basePath)
                .body(FileUtils.readAllContentFromFile(
                        System.getProperty("user.dir") +
                                "//src//test//resources//apiRequestBody//createUsersPost.json"
                ))
                .request(Method.POST)
                .then()
                .statusCode(201)
                .body(containsString("morpheus"))
                .and()
                .log().all();
    }

    @Test
    public void samplePostAPIWithResponseSeparately() throws Exception {
        String basePath = "/api/users";
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .basePath(basePath)
                .body(FileUtils.readAllContentFromFile(
                        System.getProperty("user.dir") +
                                "//src//test//resources//apiRequestBody//createUsersPost.json"
                ))
                .request(Method.POST);
        response.then().log().all();
        String responseBody = response.getBody().asPrettyString();
        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        Headers headers = response.getHeaders();

        Assert.assertEquals(statusCode, 201);
        Assert.assertTrue(responseBody.contains("morpheus"));

    }


    @Test(dataProvider = "createUserData", dataProviderClass = DataProviderUtils.class)
    public void samplePostAPIWithDynamicData(String name, String job) throws Exception {
        String basePath = "/api/users";
        String body = FileUtils.readAllContentFromFile(
                System.getProperty("user.dir") +
                        "//src//test//resources//apiRequestBody//createUsersPost.json"
        );
        body = body.replace("${firstName}", name);
        body = body.replace("${job}", job);
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .basePath(basePath)
                .body(body)
                .request(Method.POST);
        response.then().log().all();
        String responseBody = response.getBody().asPrettyString();
        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        Headers headers = response.getHeaders();

        Assert.assertEquals(statusCode, 201);
        Assert.assertTrue(responseBody.contains(name));
        Assert.assertTrue(responseBody.contains(job));


    }

    @Test
    public void samplePutAPITest() {
        String basePath = "/api/users/2";
        given()
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .basePath(basePath)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}\n")
                .request(Method.PUT)
                .then()
                .statusCode(200)
                .body(containsString("zion resident"))
                .and()
                .log().all();
    }
}
