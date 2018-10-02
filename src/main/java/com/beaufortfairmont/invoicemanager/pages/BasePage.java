package com.beaufortfairmont.invoicemanager.pages;

import org.openqa.selenium.WebDriver;

public class BasePage extends Page{

    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }

    public Sidebar getSideBar() {
        return new Sidebar(this.getWebDriver());
    }

}
