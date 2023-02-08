package API.Data_Provider;

import API.ReusableMethods_validation;
import File.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class GetData {





    public static Object[][] getTheData(){
        Object[] [] obj = new Object[][] {{"11111", "bha"},{"22222", "nah"},{"33333", "kha"}};
       return obj;
    }
}
