package com.beaufortfairmont.invoicemanager.pages;

import com.beaufortfairmont.invoicemanager.models.Invoice;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.cssSelector;

public class InvoiceListingPage extends BasePage {
    public InvoiceListingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static InvoiceListingPage create(WebDriver driver) {
        return new InvoiceListingPage(driver);
    }

    private List<Invoice> getInvoices() {
        return findAll(className("invoice"))
                .stream()
                .map(Invoice::from)
                .collect(Collectors.toList());
    }

    public InvoiceDetailsPage open(Invoice invoice) {
        find(cssSelector("#invoiceNo_" + invoice.getInvoiceNumber() + " a")).click();
        return InvoiceDetailsPage.create(getWebDriver());
    }

    /**
     * Checks if an invoice exists on the page matching all fields
     * @param
     * @return
     */
    public boolean contains(Invoice invoice) {
        return getInvoices().contains(invoice);
    }
}
