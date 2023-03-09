package com.tutorialsninja.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;

import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO,testName + "started executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.PASS,testName + "got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();

        System.out.println("Screenshot taken");
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenPath = System.getProperty("user.dir")+ "\\Screenshots\\"+testName+".png";
        try {
            FileHandler.copy(screenShot,new File((destinationScreenPath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extentTest.addScreenCaptureFromPath(destinationScreenPath);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL,testName+" got failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, testName +" got skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        try {
            extentReports = ExtentReporter.generateExtentReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
