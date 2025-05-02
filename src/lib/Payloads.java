package src.lib;

import cucumber.api.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Payloads {
    public String getAPIUrl(String getAPIsURL) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = lib.APIList.class;
        Field field = clazz.getField(getAPIsURL);
        Object value = field.get(null);
        System.out.println("SITE_URL: " + value);
        return value.toString();
    }

    public Map<String, Object> getHeaders(DataTable table) {
        Map<String,Object> headerMap = new HashMap<>();
        List<List<String>> data =table.raw();
        for(int i=0;i<data.size();i++){
            if(Objects.equals(data.get(i).get(0), "header")){
                headerMap.put(data.get(i).get(1).trim(),data.get(i).get(2).trim());
                System.out.println("Send Header as: "+data.get(i).get(1).trim()+" "+data.get(i).get(2).trim());
            }
        }
        return headerMap;
    }

    public void verifyResponces(Response response, DataTable table) {
        List<List<String>> data =table.raw();
        String body = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);
        for(int i=0;i<data.size();i++){
            Assert.assertEquals(jsonPath.getString(data.get(i).get(0).trim()),data.get(i).get(1).trim());
            System.out.println("Actual values: "+jsonPath.getString(data.get(i).get(0).trim())+" Expected Value: "+data.get(i).get(1).trim());
        }
    }
}
