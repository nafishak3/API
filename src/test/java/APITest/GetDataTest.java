package APITest;

import API.Data_Provider.GetData;
import API.ReusableMethods_validation;
import File.Payload;
import Libary.DynamicJson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetDataTest {
    @Test(dataProvider = "getdata")
    public static void addBookWithObject(String aisle, String isbn ){

        RestAssured.baseURI ="https://rahulshettyacademy.com/";
        String response = given().when().header("Content-Type", "application/json")
                .body(Payload.libraryBookPara(aisle, isbn))
                .when()
                .post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = ReusableMethods_validation.rowToJson(response);
        String idNum = js.get("ID");
        System.out.println("This is the ID number: "+idNum);
    }
    @DataProvider(name = "getdata")
    public Object[][] getDataTest(){
       return GetData.getTheData();
    }
}
