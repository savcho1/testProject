package api;

import com.fasterxml.jackson.core.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import ui.exampletests.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class BaseApiTest extends BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = properties.getProperty("baseAPI");
    }

    public Response get(String url){
        return RestAssured.given().get(url);
    }

    public Response post(Object body){
        return  RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .post("/posts");
    }

    public Response put(String url, Object body){
        return  RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .put(url);
    }

    public Response patch(String url, Object body){
        return  RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .patch(url);
    }

    public Response delete(String url){
        return RestAssured.delete(url);
    }

    public String readSchema(String path) {
        try {
            return FileUtils.readFileToString(new File(path), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public String convertResponsetoOnject(JsonParser response, Class c) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            objectMapper.readValues(response, c);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
