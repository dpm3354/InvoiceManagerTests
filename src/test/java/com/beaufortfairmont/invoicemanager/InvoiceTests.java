package com.beaufortfairmont.invoicemanager;

import com.beaufortfairmont.invoicemanager.models.ApplicationConfiguration;
import com.beaufortfairmont.invoicemanager.models.Invoice;
import com.beaufortfairmont.invoicemanager.pages.AddInvoicePage;
import com.beaufortfairmont.invoicemanager.pages.InvoiceListingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class InvoiceTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationConfiguration configuration;

    @DataProvider
    public Object[][] invoiceMatrix() {
        Object[][] invoices = new Object[1][1];

        invoices[0][0] = Invoice
                .builder()
                .companyName("All Day Plumbing")
                .invoiceNumber("10030")
                .typeOfWork("Plumbing")
                .status(Invoice.Status.DRAFT)
                .price(new BigDecimal("75.00"))
                .companyName("Installed toilet handle.")
                .dueDate(LocalDate.of(2016,4, 30));


        return invoices;
    }

	@Test(dataProvider = "invoiceMatrix")
	public void addInvoice(Invoice invoice) {
        InvoiceListingPage.create(null)
                .getSideBar()
                .clickAddInvoice()
                .create(invoice);
    }

}
