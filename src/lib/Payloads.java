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
        Class<?> clazz = src.lib.APIList.class;
        Field field = clazz.getField(getAPIsURL);
        Object value = field.get(null);
        return value.toString();
    }

    public Map<String, Object> getHeaders(DataTable table) {
        Map<String,Object> headerMap = new HashMap<>();
        List<List<String>> data =table.raw();
        for (List<String> datum : data) {
            if (Objects.equals(datum.get(0), "header")) {
                headerMap.put(datum.get(1).trim(), datum.get(2).trim());
                System.out.println("Send Header as: " + datum.get(1).trim() + " " + datum.get(2).trim());
            }
        }
        return headerMap;
    }
    public Map<String, Object> getBody(DataTable table) {
        Map<String,Object> bodyMap = new HashMap<>();
        List<List<String>> data =table.raw();
        for (List<String> datum : data) {
            if (Objects.equals(datum.get(0), "body")) {
                bodyMap.put(datum.get(1).trim(), datum.get(2).trim());
                System.out.println("Send body as: " + datum.get(1).trim() + " " + datum.get(2).trim());
            }
        }
        return bodyMap;
    }
    public void verifyResponces(Response response, DataTable table) {
        List<List<String>> data =table.raw();
        String body = response.getBody().asString();
        System.out.println("Body as: "+body);
        JsonPath jsonPath = new JsonPath(body);
        for (List<String> datum : data) {
            Assert.assertEquals(jsonPath.getString(datum.get(0).trim()), datum.get(1).trim());
            System.out.println("Actual values: " + jsonPath.getString(datum.get(0).trim()) + " Expected Value: " + datum.get(1).trim());
        }
    }
}
