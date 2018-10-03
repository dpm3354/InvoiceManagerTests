package com.beaufortfairmont.invoicemanager.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InvoiceDetailsPage extends BasePage {
    private InvoiceDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static InvoiceDetailsPage create(WebDriver webDriver) {
        return new InvoiceDetailsPage(webDriver);
    }

    public InvoiceListingPage delete() throws InterruptedException {
        find(By.id("deleteButton")).click();
        return InvoiceListingPage.create(getWebDriver());
    }
}
