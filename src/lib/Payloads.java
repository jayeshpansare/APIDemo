package src.lib;

import cucumber.api.DataTable;
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

    public Map<String, ?> getHeaders(DataTable table) {
        Map<String, String> headers = new HashMap<>();
        List<List<String>> data =table.raw();
        for(int i=0;i<=data.size();i++){
            if(Objects.equals(data.get(i).get(0), "header")){
                headers.put(data.get(i).get(1).trim(),data.get(i).get(2).trim());
            }
        }
        return headers;
    }
}
