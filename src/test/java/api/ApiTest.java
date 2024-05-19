package api;

//import api.dto.request.PostRequest;
//import api.dto.request.PostRequestDtoBuilder;
import api.dto.response.Post1Response;
import api.dto.request.Post1Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constants;

public class ApiTest extends BaseApiTest{


    @Test
    public void verifyGetResponse() throws JsonProcessingException {
        String expectedSchema = readSchema(Constants.JSON_SCHEMA_POST_1);

        Response getResponse = get("/posts/1");
        Assert.assertEquals(getResponse.statusCode(), 200);
        //json schema
        getResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(expectedSchema));

        //json path
        JsonPath extractor = getResponse.jsonPath();
        Assert.assertTrue(extractor.get("id").equals(1));
        int userId = extractor.get("userId");
        Assert.assertEquals(userId, 1);

        //DTO
        Post1Response responsePost1Response = getResponse.as(Post1Response.class);
        Assert.assertEquals(responsePost1Response.getId(), 1);


        String jsonResponse = getResponse.asString();

        // Parse the JSON string to a Java object using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        Post1Response responsePost1ResponseJackson = objectMapper.readValue(jsonResponse, Post1Response.class);
        Assert.assertEquals(responsePost1ResponseJackson.getId(), 1);
    }

    @Test
    public void postJecksonTest(){
        Post1Request post1Request = new Post1Request.Builder()
                .userId(123)
                .body("John Doe")
                .title("john@example.com")
                .build();
//        post1.setUserId(123);
//        post1.setBody("John Doe");
//        post1.setTitle("john@example.com");

        // Serialize the Java object to JSON format
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonRequestBody = objectMapper.writeValueAsString(post1Request);
            Response responsePost = post(jsonRequestBody);

            Assert.assertEquals(responsePost.statusCode(), 201);

            Post1Response responsePostJackson = responsePost.as(Post1Response.class);

            Assert.assertEquals(responsePostJackson.getUserId(), 123);
            Assert.assertEquals(responsePostJackson.getBody(), "John Doe");
            Assert.assertEquals(responsePostJackson.getTitle(), "john@example.com");
            System.out.println(responsePostJackson.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void postGsonTest(){
        Post1Request post1Request = new Post1Request.Builder()
                .userId(123)
                .body("John Doe")
                .title("john@example.com")
                .build();
//        post1.setUserId(123);
//        post1.setBody("John Doe");
//        post1.setTitle("john@example.com");

        // Serialize the Java object to JSON format
        Gson gson = new Gson();
        String jsonRequestBody = gson.toJson(post1Request);

        Response responsePost = post(jsonRequestBody);

        Assert.assertEquals(responsePost.statusCode(), 201);

        Post1Response responsePostJeckson = responsePost.as(Post1Response.class);

        Assert.assertEquals(responsePostJeckson.getUserId(), 123);
        Assert.assertEquals(responsePostJeckson.getBody(), "John Doe");
        Assert.assertEquals(responsePostJeckson.getTitle(), "john@example.com");
        System.out.println(responsePostJeckson.getId());
    }

    @Test
    public void putGsonTest(){
        Post1Request post1Request = new Post1Request.Builder().userId(12311)
                .body("John Doe11")
                .title("john@example.com11")
                .build();
//        post1.setUserId(12311);
//        post1.setBody("John Doe11");
//        post1.setTitle("john@example.com11");

        // Serialize the Java object to JSON format
        Gson gson = new Gson();
        String jsonRequestBody = gson.toJson(post1Request);

        Response responsePut = put("posts/1", jsonRequestBody);

        Assert.assertEquals(responsePut.statusCode(), 200);

        Post1Response responsePutGson = responsePut.as(Post1Response.class);

        Assert.assertEquals(responsePutGson.getUserId(), 12311);
        Assert.assertEquals(responsePutGson.getBody(), "John Doe11");
        Assert.assertEquals(responsePutGson.getTitle(), "john@example.com11");
        Assert.assertEquals(responsePutGson.getId(), 1);
    }
//
//    @Test
//    public void patchTest(){
//
//        PostRequest postRequest = new PostRequestDtoBuilder().title("Title").build();
//
//        Response responsePatch = patch("posts/1", postRequest);
//
//        System.out.println(responsePatch.asString());
//
//        Assert.assertEquals(responsePatch.statusCode(), 200);
//
//        Post1Response responsePatch1 = responsePatch.as(Post1Response.class);
//
//        Assert.assertEquals(responsePatch1.getId(), 1);
//        Assert.assertEquals(responsePatch1.getTitle(), "Title");
//    }

    @Test
    public void deleteGsonTest(){
        Response responseDelete = delete("posts/1");

        Assert.assertEquals(responseDelete.statusCode(), 200);
    }
}
