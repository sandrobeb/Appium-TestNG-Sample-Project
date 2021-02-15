package testing.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.tinylog.configuration.Configuration;
import testing.core.Utils.*;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})

public class BaseRunner {

    public static ThreadLocalDriver threadLocalDriver = new ThreadLocalDriver();
    private static AppiumDriverLocalService appiumDriverLocalService;
    public  AndroidDriver driver;

    /**
     * This class sets logging logic
     */
    @BeforeSuite
    public void initiateServices() {

        /*
        Set Logger config
        /logs/main_<date>.log
         */
        Configuration.set("writer.file", System.getProperty("user.dir") + "/logs/" + "main_" + Utilities.returnCurrentTimeToDateFormat() + ".log");
        Configuration.set("writer.level", "debug");
        Configuration.set("writingthread", "true");
    }

    /**
     * This method is called each time a test starts. It sets desired capabilities
     *
     * @param udid
     * @throws MalformedURLException
     */
    @BeforeMethod
    @Parameters({"udid"})
    public void Runner(@Optional String udid) throws MalformedURLException {

        URL remoteURL = new URL("http://localhost:4723/wd/hub");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        /* enter device name in udid / check with command "adb devices "in the terminal */
        udid = "";

        // enter path to apk
        desiredCapabilities.setCapability("app", "");
        desiredCapabilities.setCapability("deviceName", udid);
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("appWaitActivity", "cz.bsc.mb.activities.*");
        desiredCapabilities.setCapability("autoGrantPermissions", true);

        // set the driver
        threadLocalDriver.setTlDriver(new AndroidDriver(remoteURL, desiredCapabilities));
        driver = threadLocalDriver.getTLDriver();
    }

    @AfterMethod
    public synchronized void tearDown() {
        // quit the driver
        driver.quit();
    }


}
