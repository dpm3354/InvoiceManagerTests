package com.beaufortfairmont.invoicemanager.pages;

import com.beaufortfairmont.invoicemanager.models.Invoice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.*;

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
                .map(this::from)
                .collect(Collectors.toList());
    }

    public InvoiceDetailsPage open(Invoice invoice) {
        find(cssSelector("#invoiceNo_" + invoice.getInvoiceNumber() + " a")).click();
        return InvoiceDetailsPage.create(getWebDriver());
    }

    private Invoice from(WebElement element) {
        return Invoice.builder()
                .invoiceNumber(extractText(element.findElement(className("invoice_number"))))
                .companyName(extractText(element.findElement(className("company_name"))))
                .typeOfWork(extractText(element.findElement(className("type_of_work"))))
                .dueDate(LocalDate.parse(extractText(element.findElement(className("due_date")))))
                .price(new BigDecimal(extractText(element.findElement(className("price")))))
                .description(extractText(element.findElement(className("comment"))))
                .status(Invoice.Status.parse(extractText(element.findElement(className("status")))).orElseThrow(NullPointerException::new))
                .build();
    }

    public boolean contains(Invoice invoice) {
        return getInvoices().contains(invoice);
    }
}
