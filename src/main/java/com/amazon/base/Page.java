package com.amazon.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {
	public WebDriver driver;
	public Properties config= new Properties();
	public FileInputStream fis;
	public WebDriverWait wait;
	
	public Page() {
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			config.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeBrowseAndLoadApplicationUrl() {
		if (config.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*"); 
			driver = new ChromeDriver(options);
		} else if (config.getProperty("browser").equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (config.getProperty("browser").equals("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(options);
		}

		driver.get(config.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		return driver;
	}

	public void click(WebElement element) {
		element.click();
	}

	public void type(WebElement element, String value) {
		element.sendKeys(value);
	}

	public Boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void select(WebElement dropDown, String value) {
		Select select = new Select(dropDown);
		select.selectByVisibleText(value);
	}
	
	
	
}
