package testing.core.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {


    public static String returnCurrentTimeToDateFormat() {
        long currentmill = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM_dd_yyyy_HH:mm");
        Date date = new Date(currentmill);

        return simpleDateFormat.format(date);
    }

    private static StringBuilder returnScreenshotPath() {

        // Path for screenshots
        String basePath = System.getProperty("user.dir") + "/screenshots/";

        // date format for screenshot folder
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = format.format(new Date());

        // Extend the path
        StringBuilder pathBuilder = new StringBuilder(basePath);
        pathBuilder.append("/")
                .append(dateString)
                .append("/");

        return pathBuilder;
    }

    public static String returnScreenShotNameAndFormattedTimeStamp() {

        long currentTimeMillis = System.currentTimeMillis();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(currentTimeMillis);
        String formattedCurrentTime = dateFormat.format(date);

        StringBuilder finalScreenshotName = new StringBuilder("SeleniumFailure_");
        finalScreenshotName.append(formattedCurrentTime).append(".png");

        return finalScreenshotName.toString();
    }

    // String build the out final path and screenshot name;
    public static StringBuilder finalPathAndScreenshotName(String testname) {
        StringBuilder finalPathAndScreenshotName = new StringBuilder();
        finalPathAndScreenshotName.append(Utilities.returnScreenshotPath())
                .append("/").append(testname)
                .append("/").
                append(Utilities.returnScreenShotNameAndFormattedTimeStamp());

        return finalPathAndScreenshotName;
    }



}
