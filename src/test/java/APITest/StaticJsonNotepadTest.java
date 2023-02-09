package APITest;

import API.StaticJsonNotepad;
import org.testng.annotations.Test;

public class StaticJsonNotepadTest extends StaticJsonNotepad {
    @Test
    public void getNotepadTest(){
        StaticJsonNotepad.basicsHttp();
    }
}
