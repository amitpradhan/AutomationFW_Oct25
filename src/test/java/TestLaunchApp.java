import com.automation.megamind.base.Base;
import com.automation.megamind.base.Hooks;
import com.automation.megamind.pages.LoginPage;
import org.junit.Test;

import static com.automation.megamind.base.Base.driver;


public class TestLaunchApp  {

    @Test
    public void loginTest(){
        new LoginPage(driver).login("amit");
    }
}
