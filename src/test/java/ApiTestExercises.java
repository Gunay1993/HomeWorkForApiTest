import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ApiTestExercises {

  @BeforeAll
  public static void setup() {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

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

}
