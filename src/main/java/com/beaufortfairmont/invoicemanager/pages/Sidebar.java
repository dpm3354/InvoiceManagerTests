package com.beaufortfairmont.invoicemanager.pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;

public class Sidebar extends Page {
    public Sidebar(WebDriver webDriver) {
        super(webDriver);
    }

    public AddInvoicePage clickAddInvoice() {
        find(cssSelector("[href='#/addInvoice']")).click();
        return AddInvoicePage.create(getWebDriver());
    }

    public InvoiceListingPage getInvoices() {
        find(cssSelector("[href='#/tables']")).click();
        return InvoiceListingPage.create(getWebDriver());
    }
}
