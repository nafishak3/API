package JiraTest;

import Jira.Jira;
import org.testng.annotations.Test;

public class JiraFirstComment extends Jira {
    @Test
    public void updateComment(){
        Jira.testJira();
    }
}
