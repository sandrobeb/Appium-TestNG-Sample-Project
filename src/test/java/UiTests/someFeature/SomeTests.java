package UiTests.someFeature;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import testing.core.BaseRunner;
import testing.core.pages.SomePage;
import testing.core.steps.SomeSteps;

public class SomeTests extends BaseRunner {

    @Test
    @Description("Some Test")
    public void someTest() {

        /* initializing SomeSteps and SomePage to use then in Tests */
        SomeSteps someSteps = new SomeSteps(driver);
        SomePage somePage = new SomePage();

        // Fluent interface example
        someSteps.someStep()
                .someStep()
                .someStep();

        Assert.assertEquals(driver.findElement(somePage.getLocator()), "...");
    }


}
