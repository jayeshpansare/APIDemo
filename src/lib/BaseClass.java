package src.lib;

import io.restassured.response.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    private String baseURL;
    private Response getResponse;

    public void setBaseURL(String baseURL){
        this.baseURL=baseURL;
    }
    public String getBaseURL(){
        return baseURL;
    }
    protected void setResponse(Response getResponse) {
        this.getResponse=getResponse;
    }
    public Response getResponse(){
        return getResponse;
    }
    /**
     * read property files
     * **/
    public static Properties readProperty() throws IOException {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath + "/src/config/config.properties");
        FileInputStream fin = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(fin);
        return prop;
    }
}
