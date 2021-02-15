package testing.core.pages;

import org.openqa.selenium.By;


public class SomePage {

    /*
  Save locators here
   */
    By locator = By.id("someId");

    /*
    Constructor login Page
     */
    public SomePage() {
    }


    public By getLocator() {
        return locator;
    }


}
