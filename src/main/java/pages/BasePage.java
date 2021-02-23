package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BasePage {
	WebDriver driver;
	ExtentTest test;

	public BasePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);

	}

	public void testLinks(ExtentTest test) {
		List<WebElement> data = driver.findElements(By.tagName("a"));
		List<String> brokenlinks = new LinkedList<String>();
		Iterator<WebElement> it = data.iterator();

		while (it.hasNext()) {

			String url = it.next().getAttribute("href");

			// System.out.println(url);

			if (!(url == null || url.isEmpty())) {

				try {
					HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());

					huc.setRequestMethod("HEAD");

					huc.connect();

					int respCode = huc.getResponseCode();

					if (respCode >= 400) {
						brokenlinks.add(url);
						System.out.println(url + " is a broken link");
					} /*
						 * else { System.out.println(url + " is a valid link");
						 * }
						 */

				} catch (MalformedURLException e) {
					brokenlinks.add(url);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (String broken : brokenlinks)
		{
			System.out.println(broken);
			test.log(Status.INFO, broken);

		}
	}

}
