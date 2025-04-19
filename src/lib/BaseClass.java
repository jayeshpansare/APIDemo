package lib;

import io.restassured.response.Response;

public class BaseClass {
    private String baseURL;
    private Response getResponse;

    public void setBaseURL(String baseURL){
        this.baseURL=baseURL;
    }
    public String getBaseURL(){
        return baseURL;
    }
    protected void setResponce(Response getResponse) {
        this.getResponse=getResponse;
    }
    public Response getResponse(){
        return getResponse;
    }
}
