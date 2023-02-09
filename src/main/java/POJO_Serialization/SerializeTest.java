package POJO_Serialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {
    public static void main(String[] args) {
    RestAssured.baseURI = "https://rahulshettyacademy.com";

    AddPlace addObject = new AddPlace();
        addObject.setAccuracy(50);
        addObject.setName("Frontline house");
        addObject.setPhone_number("(+91) 983 893 3937");
        addObject.setAddress("29, side layout, cohen 09");
        addObject.setWebsite("http://google.com");
        addObject.setLanguage("French-IN");

    List<String> typesList = new ArrayList<>();
        typesList.add("shoe park");
        typesList.add("shop");
        addObject.setTypes(typesList);

    Location locationObject = new Location();
        locationObject.setLat(-38.383494);
        locationObject.setLng(33.427362);
        addObject.setLocation(locationObject);


    Response response = given().log().all()
            .queryParams("key","qaclick123")
            .body(addObject)
            .when().post("/maps/api/place/add/json")
            .then().assertThat().statusCode(200)
            .extract().response();

    String responseString = response.asString();
        System.out.println(responseString);
}
}
