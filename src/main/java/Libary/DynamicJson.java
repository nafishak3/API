package Libary;

import API.ReusableMethods_validation;
import File.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {



    public static void addBook1(){
        RestAssured.baseURI ="https://rahulshettyacademy.com/";
        String reponse = given().when().header("Content-Type", "application/json")
                .body(Payload.libraryBook())
                .when()
                .post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = ReusableMethods_validation.rowToJson(reponse);
        String idNum = js.get("ID");
        System.out.println("This is the ID number"+idNum);

    }
    public static void addBookWithPara(){
        RestAssured.baseURI ="https://rahulshettyacademy.com/";
        String response = given().when().header("Content-Type", "application/json")
                .body(Payload.libraryBookPara("55050", "nkt"))
                .when()
                .post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = ReusableMethods_validation.rowToJson(response);
        String idNum = js.get("ID");
        System.out.println("This is the ID number: "+idNum);

    }


}
