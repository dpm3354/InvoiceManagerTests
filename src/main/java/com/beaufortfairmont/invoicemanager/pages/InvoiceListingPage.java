package com.beaufortfairmont.invoicemanager.pages;

import org.openqa.selenium.WebDriver;

public class InvoiceListingPage extends BasePage {
    public InvoiceListingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static InvoiceListingPage create(WebDriver driver) {
        return new InvoiceListingPage(driver);
    }
}
