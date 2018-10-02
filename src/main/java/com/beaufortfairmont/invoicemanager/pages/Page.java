package com.beaufortfairmont.invoicemanager.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Page {

    private final WebDriver webDriver;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected WebElement find(By by) {
        return getWebDriver().findElement(by);
    }
}