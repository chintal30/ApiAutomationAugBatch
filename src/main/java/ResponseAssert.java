import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseAssert {

    Response response;

    public ResponseAssert(Response response){
        this.response = response;
        this.response.then().log().all();
    }
    public ResponseAssert checkIfStatusCodeEqualsTo(int statusCode){
        Assert.assertEquals(response.getStatusCode(),statusCode);
        return this;
    }

    public ResponseAssert checkIfBodyContains(String keyword){
        Assert.assertTrue(response.getBody().asPrettyString().contains(keyword));
        return this;
    }

    public ResponseAssert checkIfHeadersContains(String key, String value){
        String headerValue =response.getHeader(key);
        boolean status = false;
        if(headerValue == null ||headerValue.isEmpty() ){
            status  = false;
        }else{
            status = true;
        }
        Assert.assertTrue(status);
        return this;
    }

}
