package API;

import File.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*; // given belongs here
import static org.hamcrest.Matchers.*;



public class Basics {
//validate if add place API is working as expected
//        Rest assured work's on 3 principals of API
//            given: All input details
//            when: submit the API  -- Resource and http method will go inside this
//            Then: Validate the response
        public static void main(String[] args) {


            RestAssured.baseURI = "https://rahulshettyacademy.com";
           String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                    .body(Payload.addPlace())
                    .when().post("maps/api/place/add/json")
                    .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                    .header("server", "Apache/2.4.41 (Ubuntu)")

      // new case: Add place --> update place with new address --> get place to validate if new address is present
                    .extract().response().asString();

            System.out.println(response);
// java script object notation
            JsonPath js = new JsonPath(response); //for passing json
            String placeID = js.getString("place_id");
            System.out.println("place_id printed separately on console: " + placeID);

        //  update place
            String newAddress = "Summer walk, BD";
            given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                    .body("{\n" +
                            "\"place_id\":\""+placeID+"\",\n" +
                            "\"address\":\""+newAddress+"\",\n" +
                            "\"key\":\"qaclick123\"\n" +
                            "}")
                    .when().put("maps/api/place/update/json")
                    .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //  get place
            String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
            .queryParam("place_id", placeID)
                    .when().get("maps/api/place/get/json")
                    .then().assertThat().log().all().statusCode(200).extract().response().asString();

//            JsonPath js1 = new JsonPath(getPlaceResponse);
//            String actualAdres = js1.getString("address");
//            System.out.println("New address: " + actualAdres);
            JsonPath js1 = ReusableMethods_validation.rowToJson(getPlaceResponse);
            String actualAdres = js1.getString("address");
            System.out.println("New address: " + actualAdres);

        // Testing: Junit and Testng
            Assert.assertEquals(actualAdres, newAddress);

            System.out.println("*************************************************************************");













}


}
