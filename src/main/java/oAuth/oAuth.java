package oAuth;

import API.ReusableMethods_validation;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class oAuth {
    public static void main(String[] args) {

/*       WebDriver driver = new ChromeDriver();
//        driver.get("https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qj7PanG1C2lnLnYuWTgb8niyhzXcIdsuI385e57LwmJwZmCnuHr3UHu51oiGuplvw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none");
//        driver.findElement(By.id("identifierId")).sendKeys("ss.qa@gmail.com");
//        driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
//        driver.findElement(By.name("password")).sendKeys("xxxxxxxxxx");
//        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
//        String url = driver.getCurrentUrl();
//        String partialCode = url.split("code=")[1];
//        String code = partialCode.split("&scope")[0];
//        System.out.println(code);
 */
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qj7PanG1C2lnLnYuWTgb8niyhzXcIdsuI385e57LwmJwZmCnuHr3UHu51oiGuplvw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println(code);

        //For access token
        String excessTokenResponse = given().urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token")
                .asString();
        JsonPath js = ReusableMethods_validation.rowToJson(excessTokenResponse);
        String accessToken = js.getString("access_token");


        //Get response
        String oAuthResponse= given().queryParam("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .asString();

        System.out.println(oAuthResponse);

    }
}
