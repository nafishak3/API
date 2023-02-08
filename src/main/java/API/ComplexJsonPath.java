package API;

import File.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ComplexJsonPath {
    public static void main(String[] args) {

        JsonPath js = new JsonPath(Payload.coursePrice());
//        1. Print No of courses returned by API
        int countCourse = js.getInt("courses.size()");
        System.out.println(countCourse);

        System.out.println("***************************");

//        2.Print Purchase Amount
        int purcAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purcAmount);

        System.out.println("***************************");

//        3. Print Title of the first course
        String getFirstcourse = js.get("courses[0].title");
        System.out.println(getFirstcourse);

        System.out.println("***************************");


//        4. Print All course titles and their respective Prices
        for(int i=0; i<countCourse; i++){
            String tittle = js.get("courses["+i+"].title");
            int price = js.getInt("courses["+i+"].price");
            System.out.println(tittle + "- " + price);
        }

        System.out.println("***************************");

//        5. Print no of copies sold by RPA Course
        for(int y=0; y<countCourse; y++){
            String rpa = js.get("courses["+y+"].title");
            if (rpa.equalsIgnoreCase("RPA")){
               int copies = js.getInt("courses["+y+"].copies");
                System.out.println(rpa + "- " + copies);
                break;
            }
        }
        System.out.println("***************************");

//        6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for (int n = 0; n<countCourse; n++){
            int priceCourses = js.getInt("courses["+n+"].price");
            int copiesSum = js.getInt("courses["+n+"].copies");
            int times = priceCourses*copiesSum;
            System.out.println(times);
            sum = sum + times;
        }
        System.out.println(sum);
        if(sum==purcAmount){
            System.out.println("correct");
        }else {
            System.out.println("incorrect");
        }
        Assert.assertEquals(sum, purcAmount);







    }
//      6. Verify if Sum of all Course prices matches with Purchase Amount
    public static void sumOfNum() {
//        JsonPath js = new JsonPath(Payload.coursePrice());
        JsonPath js1 =ReusableMethods_validation.rowToJson(Payload.coursePrice());
        int countCourse = js1.getInt("courses.size()");
        int purcAmount = js1.getInt("dashboard.purchaseAmount");
        int sum = 0;
        for (int n = 0; n < countCourse; n++) {
            int priceCourses = js1.getInt("courses[" + n + "].price");
            int copiesSum = js1.getInt("courses[" + n + "].copies");
            int times = priceCourses * copiesSum;
            System.out.println(times);
            sum = sum + times;
        }
        System.out.println(sum);
        if (sum == purcAmount) {
            System.out.println("correct");
        } else {
            System.out.println("incorrect");
        }
        Assert.assertEquals(sum, purcAmount);

    }
}
