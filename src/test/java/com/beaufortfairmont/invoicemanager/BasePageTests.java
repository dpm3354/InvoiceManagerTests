package com.beaufortfairmont.invoicemanager;

import com.beaufortfairmont.invoicemanager.models.ApplicationConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public abstract class BasePageTests {

    static {
        System.setProperty("webdriver.chrome.driver", BasePageTests.class.getResource("/chromedriver").getFile());
    }

    private WebDriver driver;

    protected ApplicationConfiguration configuration;

    @BeforeClass
    public void setupClass() {
        configuration = ApplicationConfiguration.initialize();
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        getDriver().navigate().to(configuration.getBaseUrl());
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        getDriver().close();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
