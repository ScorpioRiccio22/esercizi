package LabAcademy.TestSelenium;

import java.net.URISyntaxException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Configurazione 
{
	private WebDriver webDriver;
	enum Browser
	{
		CHROME, FIREFOX, EDGE
	}
	
	public WebDriver SeleniumTutorial(Browser browser) throws URISyntaxException
	{
		//setProprierties();
		switch (browser) 
		{
			case CHROME:
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications", "--start-maximized");
				webDriver = new ChromeDriver(options);
				return webDriver;
				
			case FIREFOX:
                webDriver = new FirefoxDriver();
                return webDriver;

            case EDGE:
                webDriver = new EdgeDriver();
                return webDriver;

            default:
                throw new IllegalArgumentException("Browser non supportato: " + browser);
		}
	}
	
	/*private void setProprierties() 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fasatus\\eclipse-workspace\\WebDrivers\\chromedriver-win64\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Fasatus\\eclipse-workspace\\WebDrivers\\geckodriver-v0.35.0-win-aarch64");
		System.setProperty("webdriver.edge.driver", "C:\\Users\\Fasatus\\eclipse-workspace\\WebDrivers\\geckodriver-v0.35.0-win-aarch64");
	}*/
	
}