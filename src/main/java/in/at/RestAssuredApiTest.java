package in.at;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredApiTest {

    /**
     * Sets the base URI for all API requests before running any test methods.
     */
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Sends a GET request to /posts and verifies status code and server header.
     * Logs both request and response for debugging.
     */
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

    /**
     * Sends a GET request to /posts and parses the response using JsonPath.
     * Extracts and prints the title of the first post.
     */
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

    /**
     * Sends a GET request to /posts/{id} using path parameter.
     * Extracts and prints the title of the specified post.
     */
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

    /**
     * Sends a GET request to /posts/{id}/comments to fetch comments for a specific post.
     * Verifies response structure, size, and fields.
     */
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

    /**
     * Sends a GET request to /comments using query parameter ?postId=1.
     * Verifies that all comments belong to the correct post and contain expected fields.
     */
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

    /**
     * Sends a POST request to /posts to create a new post.
     * Verifies that the response contains correct data and an ID is generated.
     */
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

    /**
     * Sends a PUT request to /posts/1 with a POJO to update a post.
     * Verifies that the updated fields are correctly reflected in the response.
     */
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

    /**
     * Sends a PATCH request to /posts/1 to update only the title field.
     * Verifies that only the title is updated and other fields remain unchanged.
     */
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

    /**
     * Sends a DELETE request to /posts/1 to delete a post.
     * Verifies that the response status code is 200.
     */
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