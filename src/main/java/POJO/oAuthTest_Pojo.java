package POJO;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class oAuthTest_Pojo {
    public static void main(String[] args) {

      //Get code
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qgmiBdXivf0YvnmiJX6Kz844UtEX9NBmcAYyg75jk_yPLEeWXPU1ZAVQuMlDMW69w&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];


        //Get access token
        String accessTokenResponse = given().urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token")
                .asString();

        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.get("access_token");


        //Get data as Java Object
        POJO_Response gc = given().queryParam("access_token", accessToken)
                .expect().defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/getCourse.php")
                .as(POJO_Response.class);

        System.out.println(gc.getLinkedin());
        System.out.println(gc.getInstructor());

        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
        System.out.println(gc.getCourses().getApi().get(1).getPrice());


        //Get the price of "SoapUI Webservices testing"
        for (API_courses api : gc.getCourses().getApi()) {
            if (api.getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
                System.out.println(api.getPrice());
            }
        }


        //Get the courses names of WebAutomation
        String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
        List<String> expectedList = Arrays.asList(courseTitles);

        List<String> actualList = new ArrayList<>();

        for (WebAutomation_courses webAutomation : gc.getCourses().getWebAutomation()) {
            actualList.add(webAutomation.getCourseTitle());
        }
        Assert.assertEquals(actualList, expectedList);
    }
}
