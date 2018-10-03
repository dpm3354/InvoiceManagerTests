package com.beaufortfairmont.invoicemanager;

import com.beaufortfairmont.invoicemanager.models.ApplicationConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@SpringBootTest
public abstract class BasePageTests extends AbstractTestNGSpringContextTests {

    static {
        System.setProperty("webdriver.chrome.driver", BasePageTests.class.getResource("/chromedriver").getFile());
    }

    private WebDriver driver;

    @Autowired
    protected ApplicationConfiguration configuration;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        getDriver().navigate().to(configuration.getBaseUrl());
    }

    @AfterMethod
    public void teardown() {
        getDriver().close();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
