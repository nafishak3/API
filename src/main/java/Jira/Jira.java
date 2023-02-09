package Jira;


import API.ReusableMethods_validation;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Jira {

    public static void testJira(){
        SessionFilter sessionFilter = new SessionFilter();  //it will store the response

        RestAssured.baseURI= "http://localhost:8080/";
//        Login scenario
        String jiraResponse = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
                .body("{ \"username\": \"nafishak4\", \"password\": \"khan1289\" }")
                        .log().all().filter(sessionFilter).when().post("rest/auth/1/session")
                .then().log().all().extract().response().asString();
        System.out.println("login scenario: "+jiraResponse);

  String expectedMessage = "My Last Comment.";

//  Adding comment
        String commnetADD = given().pathParams("id", "10005")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"body\": \""+expectedMessage+"\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").log().all().filter(sessionFilter).when().post("rest/api/2/issue/{id}/comment")
                .then().log().all().extract().response().then().statusCode(201).extract().response().asString();
        JsonPath js = ReusableMethods_validation.rowToJson(commnetADD);
        String commentID = js.getString("id");

//  Add attachment
        given().header("X-Atlassian-Token", "no-check").filter(sessionFilter)
                .pathParams("id", "10005")
                .header("Content-Type","multipart/form-data")                                        //for multipart method
                .multiPart("file", new File("src/main/java/Jira.txt"))                          //multiplePart method
                .when().post("/rest/api/2/issue/{id}/attachments")
                .then().log().all().assertThat().statusCode(200);

//  get Issue
        String issueDetails= given().filter(sessionFilter).pathParams("id", "10005")
                .queryParam("fields", "comment")
                .log().all()
                .when().get("/rest/api/2/issue/{id}")
                .then().log().all().extract().response().asString();
        System.out.println("get Issue: "+issueDetails);

//  retrieve if comment is available using unique ID
        JsonPath js1 = ReusableMethods_validation.rowToJson(issueDetails);
        int count = 0;
        int commentCount = js1.get("fields.comment.comments.size()");
        for(int i=0; i<commentCount; i++){
            String getID = js1.get("fields.comment.comments["+i+"].id").toString();
            System.out.println(count+"-->  "+getID);
            count++;
            if(getID.equalsIgnoreCase(commentID)){
                String message = js1.get("fields.comment.comments["+i+"].body").toString();
                Assert.assertEquals(expectedMessage, message);
            }
        }





    }



}

