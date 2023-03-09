package com.tutorialsninja.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ExtentReporter {
    public static ExtentReports generateExtentReport() throws IOException {
        ExtentReports extentReports = new ExtentReports();
        File extentReportFile = new File("test-output/ExtentReports/extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("TutorialsNinja Test Automation Results");
        sparkReporter.config().setDocumentTitle("TN Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");

        extentReports.attachReporter(sparkReporter);

        Properties configProp = new Properties();
        File configPropFile = new File("src/main/java/com/tutorialsninja/qa/config/config.properties");
        try {
            FileInputStream fisConfigProp = new FileInputStream(configPropFile);
            configProp.load(fisConfigProp);
        } catch (Throwable e){
            e.printStackTrace();
        }
        extentReports.setSystemInfo("Application URL",configProp.getProperty("url"));
        extentReports.setSystemInfo("Browser",configProp.getProperty("browser"));
        extentReports.setSystemInfo("Email", configProp.getProperty("validEmail"));
        extentReports.setSystemInfo("Password", configProp.getProperty("validPassword"));
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("Username",System.getProperty("user.name"));
        extentReports.setSystemInfo("Java version",System.getProperty("java.version"));

        return extentReports;
    }
}
