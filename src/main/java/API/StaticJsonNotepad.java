package API;

import File.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class StaticJsonNotepad {

    public static void basicsHttp(){
//        Covert content of the file to string--> content of files can be converted into files
//        --> Byte data to stirng
        RestAssured.baseURI= "https://rahulshettyacademy.com";
        String notepad = null;
        try {
            notepad = given().queryParam("key", "qaclick123")
                    .header("Content-Type", "application/json")
                    .body(new String(Files.readAllBytes(Paths.get(Payload.notePath()))))
                    .when().log().all().post("maps/api/place/add/json")
                    .then().log().all().statusCode(200).body("scope",equalTo("APP"))
                    .header("server", "Apache/2.4.41 (Ubuntu)")
                    .extract().response().asString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(notepad);
        JsonPath js = ReusableMethods_validation.rowToJson(notepad);
        String placeID= js.get("place_id");
        System.out.println(placeID);


    }
}
