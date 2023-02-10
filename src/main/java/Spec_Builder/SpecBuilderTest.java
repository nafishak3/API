package Spec_Builder;

import POJO_Serialization.AddPlace;
import POJO_Serialization.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
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

        RequestSpecification requestObject = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
//                it hold all given
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();


        Location locationObject = new Location();
        locationObject.setLat(-38.383494);
        locationObject.setLng(33.427362);
        addObject.setLocation(locationObject);

        ResponseSpecification respondSpecification = new ResponseSpecBuilder()
//                Hold all then
                .expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification res2 = given().spec(requestObject)
                .body(addObject);

                 Response response = res2.when().post("/maps/api/place/add/json")
                .then().spec(respondSpecification)
                .extract().response();

        String responseString = response.asString();
        System.out.println(responseString);
    }
}
