package APITest;

import Libary.DynamicJson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJasonTest extends DynamicJson {

    @Test(priority = 1)
    public void getIDTest1(){
        DynamicJson.addBook1();
    }
    @Test(priority = 2)
    public void getIDTest(){
        DynamicJson.addBookWithPara();
    }

}
