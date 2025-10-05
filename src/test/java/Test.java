import com.automation.megamind.base.Base;
import com.automation.megamind.pages.LoginPage;

import static com.automation.megamind.base.Base.launchApp;

public class Test extends Base {

    public static void main(String[] args) {
        launchApp();
        new LoginPage(driver).login();
    }




}
