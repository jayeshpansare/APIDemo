package stepDefinition;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lib.BaseClass;
import io.restassured.*;
import io.restassured.response.*;
import lib.Payloads;
import org.testng.Assert;
import page.DatabaseUtil;
import java.io.IOException;

public class TestDemo extends BaseClass {

    Payloads payload = new Payloads();
    DatabaseUtil database=new DatabaseUtil();
    @Given("^I have get \"([^\"]*)\"$")
    public void i_have_get(String baseURL) throws IOException {
        setBaseURL(readProperty().getProperty("STAGEURL"));
    }

    @When("^I have \"([^\"]*)\" using get method$")
    public void i_have_get_using_get_method(String getURLs, DataTable table) throws NoSuchFieldException, IllegalAccessException {
        String[] getAPIsURLS = getURLs.split("/");
        String getURL= payload.getAPIUrl(getAPIsURLS[1]);
        System.out.println("SITE_URL: "+getBaseURL()+getURL);
        Response getResponse = RestAssured
               .given()
               .headers(payload.getHeaders(table))
               .when()
               .get(getBaseURL()+getURL);
       setResponse(getResponse);
    }

    @Then("^Verify \"([^\"]*)\" error message$")
    public void verify_error_message(String statusCode){
        if(!statusCode.isEmpty()){
            int statusCod = Integer.parseInt(statusCode);
            Assert.assertEquals(getResponse().getStatusCode(), statusCod);
        }
    }

    @Then("^Verify response$")
    public void verifyResponse(DataTable table) {
        payload.verifyResponces(getResponse(), table);
    }

    @When("^I have \"([^\"]*)\" using post method$")
    public void iHaveUsingPostMethod(String getURLs, DataTable table) throws Throwable {
        String[] getAPIsURLS = getURLs.split("/");
        String getURL= payload.getAPIUrl(getAPIsURLS[1]);
        System.out.println("SITE_URL: "+getBaseURL()+getURL);
        Response getResponse = RestAssured
                .given()
                .headers(payload.getHeaders(table))
                .body(payload.getBody(table))
                .when()
                .post(getBaseURL()+getURL);
        setResponse(getResponse);
    }

    @When("^I have \"([^\"]*)\" using update method$")
    public void iHaveUsingUpdateMethod(String getURLs, DataTable table) throws Throwable {
        String URL;
        String[] getAPIsURLS = getURLs.split("/");
        System.out.println(getAPIsURLS.length);
        String getURL= payload.getAPIUrl(getAPIsURLS[1]);
        if(getAPIsURLS.length>2){
            if(getAPIsURLS[2].isEmpty()){
                URL=getURL;
            }else {
                URL=getURL.replaceAll(getAPIsURLS[2],database.getUserId(getAPIsURLS[2]));
            }
        }else{
            URL=getURL;
        }
        System.out.println("SITE_URL: "+URL);
        Response getResponse = RestAssured
                .given()
                .headers(payload.getHeaders(table))
                .body(payload.getBody(table))
                .when()
                .put(getBaseURL()+URL);
        setResponse(getResponse);
    }

    @When("^I have \"([^\"]*)\" using delete method$")
    public void iHaveUsingDeleteMethod(String getURLs, DataTable table) throws Throwable {
        String URL;
        String[] getAPIsURLS = getURLs.split("/");
        String getURL= payload.getAPIUrl(getAPIsURLS[1]);
        if(getAPIsURLS.length>2){
            if(getAPIsURLS[2].isEmpty()){
                URL=getURL;
            }else {
                URL=getURL.replace(getAPIsURLS[2],database.getUserId(getAPIsURLS[2]));
            }
        }else{
            URL=getURL;
        }
        System.out.println("SITE_URL: "+getBaseURL()+URL);
        Response getResponse = RestAssured
                .given()
                .headers(payload.getHeaders(table))
                .when()
                .delete(getBaseURL()+URL);
        setResponse(getResponse);
    }

    @When("^I have set username$")
    public void iHaveSetUsername() {
        setParameter("nameValue", "test");
    }
    @When("^I have set job$")
    public void iHaveSetJob() {
        setParameter("jobValue", "leader");
    }
}
