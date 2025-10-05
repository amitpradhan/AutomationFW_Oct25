import com.automation.megamind.base.Base;
import com.automation.megamind.base.Hooks;
import com.automation.megamind.pages.LoginPage;
import org.junit.Test;



public class TestLaunchApp extends Hooks {

    @Test
    public void loginTest(){
        new LoginPage(driver).login();
    }
}
