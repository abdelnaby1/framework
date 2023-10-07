package com.selenium.framework.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.selenium.framework.listener.TestListener;
import com.selenium.framework.util.Config;
import com.selenium.framework.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(TestListener.class)
public abstract class AbstractTest {
    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

//    @Parameters("browser")
    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException {
     this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))
             ? getRemoteDriver() : getLocalDriver();
     ctx.setAttribute(Constants.DRIVER, this.driver);
     this.driver.manage().window().maximize();

    }
    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities caps = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            caps = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        return new RemoteWebDriver(new URL(url),caps);

    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

//    @AfterMethod
//    public void sleep(){
//        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
//    }
}
