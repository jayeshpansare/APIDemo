package src.stepDefinition;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import src.lib.APIList;
import src.lib.BaseClass;
import io.restassured.*;
import io.restassured.response.*;
import src.lib.Payloads;
import org.testng.Assert;

public class test extends BaseClass {
    Payloads payload = new Payloads();
    @Given("^I have get \"([^\"]*)\"$")
    public void i_have_get(String baseURL){
        setBaseURL("https://reqres.in");
    }

    @When("^I have get \"([^\"]*)\" using get method$")
    public void i_have_get_using_get_method(String getURLs, DataTable table) throws NoSuchFieldException, IllegalAccessException {
        String[] getAPIsURLS = getURLs.split("/");
        String getURL= payload.getAPIUrl(getAPIsURLS[1]);
       Response getResponse = RestAssured
               .given()
               .headers(payload.getHeaders(table))
               .when()
               .get(getBaseURL()+getURL);
       setResponse(getResponse);
    }

    @Then("^Verify \"([^\"]*)\" error message$")
    public void verify_error_message(String statusCode){
        int statusCod = Integer.parseInt(statusCode);
        Assert.assertEquals(getResponse().getStatusCode(), statusCod);
        System.out.println(getResponse().body().asString());
    }

    @Then("^Verify response$")
    public void verifyResponse(DataTable table) {
        payload.verifyResponces(getResponse(), table);
    }
}
