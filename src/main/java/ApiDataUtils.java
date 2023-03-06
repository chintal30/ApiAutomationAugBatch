import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class ApiDataUtils
{

    public static void loadTestData(String testCaseName) throws IOException, ParseException {
        File jsonFile = new File(
                System.getProperty("user.dir")
                        + "//src//test//resources//apiTestData//testData.json");

        JSONParser parser = new JSONParser();
        Object object =parser.parse(new FileReader(jsonFile));
        JSONObject jsonObject = (JSONObject)object;
        JSONObject testCaseDataObject= (JSONObject)jsonObject.get(testCaseName);

        Iterator<JSONObject> itr = testCaseDataObject.keySet().iterator();
        while(itr.hasNext()){
            Object key = (Object)itr.next();
            String value = testCaseDataObject.get(key).toString();
            if(key.toString().equalsIgnoreCase("endPoint")){
                BaseApi.config.setEndPoint(value);
            }else if(key.toString().equalsIgnoreCase("baseURI")){
                BaseApi.config.setBaseURI(value);
            }else if(key.toString().equalsIgnoreCase("portNumber")){
                BaseApi.config.setPortNumber(Integer.parseInt(value));
            }else if(key.toString().equalsIgnoreCase("body")){
                BaseApi.config.setBody(value);
            }else if(key.toString().equalsIgnoreCase("headers")){
                //BaseApi.config.setHeaders(value);
            }else if(key.toString().equalsIgnoreCase("cookies")){
                //BaseApi.config.setHeaders(value);
            }
        }


    }

    public static void main(String[] args) throws IOException, ParseException {
        ApiDataUtils.loadTestData("PostUsers");
    }
}
