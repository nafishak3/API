package APITest;

import API.ComplexJsonPath;
import org.testng.annotations.Test;

public class ComplexJasonPath_SumTest extends ComplexJsonPath {
    @Test
    public void sumValidateTest(){
       ComplexJsonPath.sumOfNum();
    }
}
