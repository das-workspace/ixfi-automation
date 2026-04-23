//my extent manager code

package com.ixfi.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    // Initialize the ExtentReports and ExtentSparkReporter
    public static void setExtent() throws IOException {
        // Setting the path for the report
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport/" + "IXFITestExecutionResultReport.html";
        sparkReporter = new ExtentSparkReporter(reportPath);

        // Load the configuration file (if exists)
        sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

        // Setting the title, report name, and other configurations (if needed)
//        sparkReporter.config().setDocumentTitle("Automation Test Report");
//        sparkReporter.config().setReportName("IXFI_Web_Prod Test Automation Report"); 
        // Customizing theme, if needed:
        // sparkReporter.config().setTheme(Theme.DARK); // For dark theme, if needed

        // Initialize the ExtentReports object and attach the SparkReporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Adding system information
        extent.setSystemInfo("HostName", "MyHost");
        extent.setSystemInfo("ProjectName", "IXFIWebProd");
        extent.setSystemInfo("Tester", "Ram_Chavan");
        extent.setSystemInfo("OS", "Win10");
        extent.setSystemInfo("Browser", "Chrome");
    }

    // Flush the extent report
    public static void endReport() {
        extent.flush(); //to close the report at the end
    }
}
