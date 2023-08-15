package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/*
	 * this method is initializing the driver on the basis of given browser name
	 * 
	 * @param browserName
	 * 
	 * @return this return the driver
	 */
	public WebDriver initDriver(Properties prop) {

		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		String browserName = prop.getProperty("browser");

		System.out.println("browser names is  : " + browserName);

		if (browserName.equals("chrome"))
		{
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if (browserName.equals("firefox"))
		{
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} 
		else 
		{
			System.out.println("please pass the right browser..." + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	
	/*
	 * get local thread copy of the driver
	 * 
	 */
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/*
	 * this method is reading the properties from the config.properties file
	 * 
	 * @return
	 */
	public Properties initProp() {

		// mvn clean install -Denv="stage" -- cmd line, jenkins
		// mvn clean install
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test case on env : " + envName);

		try {
			if (envName == null) {
				System.out.println("no env is given...hence running it on QA env...");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

			} else {
				//System.out.println("Running test cases on env: " + envName);
				switch (envName) {
				case "qa":
					 ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					 ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
//				case "uat":
//					 ip = new FileInputStream("./src/main/resources/config/uat.config.properties");
//					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("plz pass the right env name...." + envName);
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}



	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/" + System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
}
