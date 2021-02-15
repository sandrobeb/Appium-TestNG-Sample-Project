package testing.core.Utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.influxdb.dto.Point;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.tinylog.Logger;
import testing.core.ThreadLocalDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

    public static ThreadLocalDriver threadLocalDriver = new ThreadLocalDriver();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Logger.info("Starting test: " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        this.sendTestMethodStatus(iTestResult, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        this.sendTestMethodStatus(iTestResult, "FAILED");
        // attach the screenshot to the report AND save a separate file under screenshots/ for debugging
        String testName = iTestResult.getMethod().getMethodName();

        File scrFile = ((TakesScreenshot) threadLocalDriver.getTLDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(Utilities.finalPathAndScreenshotName(testName).toString()));
        } catch (IOException e) {
            Logger.error("Error copying screenshot! Stacktrace: " + e);
        }

        Allure.addAttachment(testName, "Screenshot attached");
        Path content = Paths.get(Utilities.finalPathAndScreenshotName(testName).toString());
        try (InputStream is = Files.newInputStream(content)) {

            Allure.addAttachment(testName, is);

        } catch (IOException e) {
            Logger.error("Error attaching screenshot to allure report! " + e);
        }

    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        this.sendTestMethodStatus(iTestResult, "SKIPPED");
        Logger.info("Test skipped: " + iTestResult.getMethod().getMethodName());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        this.sendTestClassStatus(iTestContext);
    }

    /*
    InfluxDB methods
     */

    private void sendTestMethodStatus(ITestResult iTestResult, String status) {
        Point point = Point.measurement("testmethod")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("testclass", iTestResult.getInstanceName())
                .tag("testname", iTestResult.getName())
                .tag("result", status)
                .addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis()))
                .build();
    }

    private void sendTestClassStatus(ITestContext iTestContext) {
        Logger.info(iTestContext.getAllTestMethods()[0].getMethodName());
        Point point = Point.measurement("testclass")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("name", iTestContext.getAllTestMethods()[0].getMethodName())
                .addField("duration", (iTestContext.getEndDate().getTime() - iTestContext.getStartDate().getTime()))
                .build();
    }
}
