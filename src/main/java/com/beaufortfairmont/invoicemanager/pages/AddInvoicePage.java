package com.beaufortfairmont.invoicemanager.pages;

import com.beaufortfairmont.invoicemanager.models.Invoice;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.className;

public class AddInvoicePage extends BasePage {

    public AddInvoicePage(WebDriver webDriver) {
        super(webDriver);
    }

    private void setInvoiceNumber(Invoice invoice) {
        find(className("#invoiceNo_add input")).sendKeys(invoice.getInvoiceNumber());
    }

    private void setCompanyName(Invoice invoice) {
        find(className("#compName_add input")).sendKeys(invoice.getCompanyName());
    }

    private void setTypeOfWork(Invoice invoice) {
        find(className("#typeofwork_add input")).sendKeys(invoice.getTypeOfWork());
    }

    private void setAmount(Invoice invoice) {
        find(className("#cost_add input")).sendKeys(invoice.getPrice().toString());
    }

    private void setStatus(Invoice invoice) {
        find(className("#selectStatus")).sendKeys(invoice.getStatus().toString());
    }

    private void setDueDate(Invoice invoice) {
        find(className("#invoice_dueDate input")).sendKeys(invoice.getDueDate().toString());
    }

    private void setDescription(Invoice invoice) {
        find(className("#comments_add input")).sendKeys(invoice.getDescription());
    }

    public void create(Invoice invoice) {
        setInvoiceNumber(invoice);
        setCompanyName(invoice);
        setTypeOfWork(invoice);
        setAmount(invoice);
        setStatus(invoice);
        setDueDate(invoice);
        setDescription(invoice);
    }



}
