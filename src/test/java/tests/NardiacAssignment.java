package tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BasePage;

public class NardiacAssignment {
	WebDriver driver;
	ExtentHtmlReporter extenthtmlReporter;
	ExtentReports extentreport;
	ExtentTest test;
	BasePage BasePage;


	@BeforeClass
	public void reportInitialize() {
		extenthtmlReporter = new ExtentHtmlReporter("./ExtentReports/Report.html");
		extentreport = new ExtentReports();
		extentreport.attachReporter(extenthtmlReporter);
		extenthtmlReporter.config().setTheme(Theme.STANDARD);

	}

	@BeforeMethod
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.nordicnaturals.com/consumers/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void closeBrowser() {

		driver.quit();

	}

	@AfterClass
	public void closeReport() {
		extentreport.flush();
	}

	@Test
	public void LoginTest() throws InterruptedException {
		test = extentreport.createTest("Login Test");
		BasePage = new BasePage(driver, test);
		BasePage.testLinks(test);

	}



	
	

}
