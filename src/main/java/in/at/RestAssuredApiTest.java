package in.at;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredApiTest {

    @BeforeClass
    public void setup() {
        // Set the base URI once for all tests
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetApiPosts() {
        given()
                .log().all()
                .when()
                .get("/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("server", "cloudflare");
    }

    @Test
    public void testGetApiPostsParseTheResponse() {
        String response =
                given()
                        .when()
                        .get("/posts")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .header("server", "cloudflare")
                        .extract().asString();

        JsonPath jsonPath = new JsonPath(response);
        System.out.println("Title of first post: " + jsonPath.getString("[0].title"));
    }

    @Test
    public void testGetApiPostsWithPathParameter() {
        int postId = 1;

        String response =
                given()
                        .pathParam("id", postId)
                        .when()
                        .get("/posts/{id}")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .header("server", "cloudflare")
                        .extract().asString();

        JsonPath jsonPath = new JsonPath(response);
        System.out.println("Title of Post ID " + postId + ": " + jsonPath.getString("title"));
    }

    @Test
    public void testGetCommentsForPost() {
        int postId = 1;

        String response =
                given()
                        .pathParam("id", postId)
                        .when()
                        .get("/posts/{id}/comments")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("size()", equalTo(5))
                        .body("[0].postId", equalTo(postId))
                        .body("[0].email", containsString("@"))
                        .body("[0]", hasKey("name"))
                        .body("[0]", hasKey("email"))
                        .extract().asString();

        JsonPath jsonPath = new JsonPath(response);
        System.out.println("Email of first comment: " + jsonPath.getString("[0].email"));
    }

    @Test
    public void testGetCommentsWithQueryParam() {
        int postId = 1;

        String response =
                given()
                        .queryParam("postId", postId)
                        .when()
                        .get("/comments")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("size()", greaterThan(0))
                        .body("postId", everyItem(equalTo(postId)))
                        .body("[0].email", containsString("@"))
                        .extract().asString();

        JsonPath jsonPath = new JsonPath(response);
        System.out.println("First comment email: " + jsonPath.getString("[0].email"));
    }

    @Test
    public void testCreatePost() {
        String requestBody = """
        {
            "title": "foo",
            "body": "bar",
            "userId": 1
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .body("id", notNullValue())
                .log().all();
    }

    @Test
    public void testUpdatePostWithPojo() {
        Post updatedPost = new Post(1, "foo", "bar", 1);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(updatedPost)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .log().body();
    }

    @Test
    public void testPatchPostTitle() {
        String patchBody = """
        {
            "title": "foo"
        }
        """;

        given()
                .contentType("application/json; charset=UTF-8")
                .accept(ContentType.JSON)
                .body(patchBody)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("foo"))
                .body("id", equalTo(1))
                .log().body();
    }

    @Test
    public void testDeletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200)
                .log().body();
    }
}
