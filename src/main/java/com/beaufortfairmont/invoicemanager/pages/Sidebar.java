package com.beaufortfairmont.invoicemanager.pages;

import org.openqa.selenium.WebDriver;

public class Sidebar extends Page {
    public Sidebar(WebDriver webDriver) {
        super(webDriver);
    }

    public AddInvoicePage clickAddInvoice() {
        return new AddInvoicePage(getWebDriver());
    }
}
