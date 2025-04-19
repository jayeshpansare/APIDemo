package stepDefinition;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lib.APIList;
import lib.BaseClass;
import io.restassured.*;
import io.restassured.response.*;
import lib.Payloads;
import org.testng.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class test extends BaseClass {
    @Given("^I have get \"([^\"]*)\"$")
    public void i_have_get(String baseURL){
        setBaseURL("https://reqres.in");
    }

    @When("^I have get \"([^\"]*)\" using get method$")
    public void i_have_get_using_get_method(String getURLs, DataTable table) throws NoSuchFieldException, IllegalAccessException {
        Payloads payload = new Payloads();
        //System.out.println(payload.getHeaders(table));
        String[] getAPIsURLS = getURLs.split("/");
        String getURL= payload.getAPIUrl(getAPIsURLS[1]);
        System.out.println(getBaseURL()+getURL);
       Response getResponse = RestAssured
               .given()
               .headers("","")
               .when()
               .get(getBaseURL()+getURL);
       setResponce(getResponse);
    }

    public static List<String> getPrivateStaticFinalStrings(Class<?> clazz) throws NoSuchFieldException, IllegalAccessException {
        List<String> stringValues = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field);
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.getType() == String.class && Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
                stringValues.add((String) field.get(null)); // Get value from static field
            }
        }
        return stringValues;
    }

    @Then("^Verify \"([^\"]*)\" error message$")
    public void verify_error_message(String statusCode){
        int statusCod = Integer.parseInt(statusCode);
        Assert.assertEquals(getResponse().getStatusCode(), statusCod);
    }
}
