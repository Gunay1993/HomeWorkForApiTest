import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ApiTestExercises {

  @BeforeAll
  public static void setup() {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";

  }

  @Test
  public void getRequest() {
    Response response = RestAssured
        .given()
        .when()
        .get("https://jsonplaceholder.typicode.com/posts/15");

    response.prettyPrint();

    response.then()
        .statusCode(200)
        .contentType("application/json; charset=utf-8")
       .body("userId", equalTo(2))
        .body("title", equalTo("abhb"));

  }

  @Test
  public void postRequest() {

    String url="https://jsonplaceholder.typicode.com/posts";

    JSONObject requestBody = new JSONObject();

    requestBody.put("title", "Yeni Post");
    requestBody.put("body", "Bu POST sorğusudur");
    requestBody.put("userId", 9);
    System.out.println(requestBody.toString());

    Response response=given().contentType(ContentType.JSON)
        .when().body(requestBody.toString())
        .post(url);

    response.prettyPrint();

    //assertion
    response.then().assertThat().statusCode(201)
        .contentType(ContentType.JSON)
        .body("title",equalTo("Yeni Post"))
        .body("userId",equalTo(9));

}

@Test
  public  void getRequestCheckTitle(){

    String url="https://jsonplaceholder.typicode.com/posts/8";

    Response response= given().when().get(url);
    response.prettyPrint();

    //assertion
  response.then().assertThat().statusCode(200)
      .body("userId", equalTo(1))
      .body("title", Matchers.containsString("dolorem"));
}

  @Test
  public  void getRequestCheckLength(){

    String url="https://jsonplaceholder.typicode.com/posts/10";

    Response response= given().when().get(url);

    response.prettyPrint();

    response.then().assertThat().statusCode(200)
        .body("title", Matchers.notNullValue())
        .body("body.length()", Matchers.greaterThan(30));

  }
}
