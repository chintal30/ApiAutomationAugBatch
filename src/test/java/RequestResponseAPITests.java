import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class RequestResponseAPITests {

    @Test
    public void CreateUsers() throws IOException, ParseException {
        ApiDataUtils.loadTestData("PostUsers");
        Response response = BaseApi.makeAPostRequestWithoutPort(BaseApi.config);
        ResponseAssert assertResponse = new ResponseAssert(response);
        assertResponse.checkIfBodyContains("id")
                .checkIfStatusCodeEqualsTo(201)
                .checkIfHeadersContains("Content-Type", "application/json; charset=utf-8");

    }
}
