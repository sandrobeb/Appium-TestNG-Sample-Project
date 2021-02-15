package testing.core;

import io.appium.java_client.android.AndroidDriver;

public class ThreadLocalDriver {

    private static ThreadLocal<AndroidDriver> tlDriver = new ThreadLocal<>();

    public synchronized void setTlDriver(AndroidDriver driver) {
        tlDriver.set(driver);
    }

    public synchronized AndroidDriver getTLDriver() {
        return tlDriver.get();
    }
}
