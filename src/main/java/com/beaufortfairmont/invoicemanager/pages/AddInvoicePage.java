package com.beaufortfairmont.invoicemanager.pages;

import com.beaufortfairmont.invoicemanager.models.Invoice;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class AddInvoicePage extends BasePage {

    private AddInvoicePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static AddInvoicePage create(WebDriver webDriver) {
        return new AddInvoicePage(webDriver);
    }

    private void setInvoiceNumber(Invoice invoice) {
        find(cssSelector("#invoiceNo_add input")).sendKeys(invoice.getInvoiceNumber());
    }

    private void setCompanyName(Invoice invoice) {
        find(cssSelector("#compName_add input")).sendKeys(invoice.getCompanyName());
    }

    private void setTypeOfWork(Invoice invoice) {
        find(cssSelector("#typeofwork_add input")).sendKeys(invoice.getTypeOfWork());
    }

    private void setAmount(Invoice invoice) {
        find(cssSelector("#cost_add input")).sendKeys(invoice.getPrice().toString());
    }

    private void setStatus(Invoice invoice) {
        find(cssSelector("#selectStatus")).sendKeys(invoice.getStatus().toString());
    }

    private void setDueDate(Invoice invoice) {
        find(cssSelector("#invoice_dueDate input")).sendKeys(invoice.getDueDate().toString());
    }

    private void setDescription(Invoice invoice) {
        find(cssSelector("#comments_add input")).sendKeys(invoice.getDescription());
    }

    public InvoiceListingPage create(Invoice invoice) {
        setInvoiceNumber(invoice);
        setCompanyName(invoice);
        setTypeOfWork(invoice);
        setAmount(invoice);
        setStatus(invoice);
        setDueDate(invoice);
        setDescription(invoice);
       return submit();
    }

    private InvoiceListingPage submit() {
        find(id("createButton")).click();
        return InvoiceListingPage.create(getWebDriver());
    }


}
