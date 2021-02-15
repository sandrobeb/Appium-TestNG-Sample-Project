package testing.core.steps;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import testing.core.pages.SomePage;


public class SomeSteps {

    private AndroidDriver driver;

    public SomeSteps(AndroidDriver driver) {
        this.driver = driver;
    }

    @Step("Some Step")
    public SomeSteps someStep() {
        SomePage loginPage = new SomePage();

          /*
    Enter step logic
     */

        return this;
    }

}
