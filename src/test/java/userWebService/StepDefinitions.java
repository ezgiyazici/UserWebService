package userWebService;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class StepDefinitions{
    String url = "http://localhost:8082/User";
    Response getResponse;

    @When("^the client calls GET \\/User")
    public void the_client_issues_GET() throws Throwable{
        getResponse = RestAssured.given()
                .get(url)
                .then()
                .extract()
                .response();


    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        Assert.assertEquals(getResponse.statusCode(),200);;
    }

    String postRequest;
    Response postResponse;

    @When("^the client calls POST \\/User")
    public void the_client_issues_POST() throws Throwable{
        postRequest = "{\n" +
                "  \"name\": \"Ezgi\",\n" +
                "  \"surname\": \"Yazıcı\",\n" +
                "  \"age\": 22 ,\n" +
                "  \"country\": \"Bursa\" \n}";
        postResponse = RestAssured.given()
                        .header("Accept", ContentType.JSON.getAcceptHeader())
                        .contentType(ContentType.JSON)
                        .body(postRequest)
                        .when()
                        .post(url)
                        .then()
                        .extract()
                        .response();

    }

    @Then("^the client receives post status code of (\\d+)$")
    public void the_client_receives_status_code_of_post_request(int statusCode) throws Throwable {
        Assert.assertEquals(postResponse.statusCode(),201);

    }
    @And("^create user response should be valid$")
    public void verify_response() {
        String name = postResponse.jsonPath().get("name");
        String surname = postResponse.jsonPath().get("surname");
        int age = postResponse.jsonPath().get("age");
        String country = postResponse.jsonPath().get("country");
        int id = postResponse.jsonPath().get("id");

        Assert.assertEquals("Ezgi", name);
        Assert.assertEquals("Yazıcı", surname);
        Assert.assertEquals(22,age);
        Assert.assertEquals("Bursa", country);
        Assert.assertNotNull(id);
    }
    String putRequest;
    Response putResponse;

    @When("^the client calls PUT \\/User\\/Id")
    public void the_client_issues_PUT() throws Throwable{
        putRequest = "{\n" +
                "  \"name\": \"Ezgi\",\n" +
                "  \"surname\": \"Yazıcı\",\n" +
                "  \"age\": 22 ,\n" +
                "  \"country\": \"Bursa\" \n}";
        putResponse = RestAssured.given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(putRequest)
                .when()
                .put(url+"/1")
                .then()
                .extract()
                .response();

    }

    @Then("^the client receives put status code of (\\d+)$")
    public void the_client_receives_status_code_of_put_request(int statusCode) throws Throwable {
        Assert.assertEquals(putResponse.statusCode(),200);

    }
    @And("^update user response should be valid$")
    public void verify_response_put_request() {
        String name = putResponse.jsonPath().get("name");
        String surname = putResponse.jsonPath().get("surname");
        int age = putResponse.jsonPath().get("age");
        String country = putResponse.jsonPath().get("country");
        int id = putResponse.jsonPath().get("id");

        Assert.assertEquals("Ezgi", name);
        Assert.assertEquals("Yazıcı", surname);
        Assert.assertEquals(22,age);
        Assert.assertEquals("Bursa", country);
        Assert.assertNotNull(id);
    }

    Response deleteResponse;

    @When("^the client calls DELETE \\/User\\/Id")
    public void the_client_issues_DELETE() throws Throwable{
        deleteResponse =  RestAssured
                        .when()
                        .delete(url+"/1")
                        .then()
                        .extract()
                        .response();


    }

    @Then("^the client receives delete status code of (\\d+)$")
    public void the_client_receives_status_code_of_delete_request(int statusCode) throws Throwable {
        Assert.assertEquals(deleteResponse.statusCode(),204);;
    }

}
