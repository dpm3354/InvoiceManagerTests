package com.beaufortfairmont.invoicemanager;

import com.beaufortfairmont.invoicemanager.models.Invoice;
import com.beaufortfairmont.invoicemanager.pages.InvoiceListingPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.beaufortfairmont.invoicemanager.models.Invoice.Status.DRAFT;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class InvoiceTests extends BasePageTests {

    @DataProvider
    public Object[][] invoiceMatrix() {
        Object[][] invoices = new Object[1][1];

        invoices[0][0] = Invoice
                .builder()
                .companyName("All Day Plumbing")
                .invoiceNumber("10030")
                .typeOfWork("Plumbing")
                .status(DRAFT)
                .price(new BigDecimal("75.00"))
                .companyName("Installed toilet handle.")
                .description("stanky")
                .dueDate(LocalDate.of(2016,4, 30))
                .build();
        return invoices;
    }

	@Test(dataProvider = "invoiceMatrix")
	public void addInvoice(Invoice invoice) {
        InvoiceListingPage invoices = InvoiceListingPage.create(getDriver())
                .getSideBar()
                .clickAddInvoice()
                .create(invoice)
                .getSideBar()
                .getInvoices();
        assertTrue(invoices.contains(invoice));
    }

    @Test(dataProvider = "invoiceMatrix", dependsOnMethods = "addInvoice", enabled = false)
    public void deleteInvoice(Invoice invoice) throws InterruptedException {
        InvoiceListingPage invoices = InvoiceListingPage
                .create(getDriver())
                .open(invoice)
                .delete();
        assertFalse(invoices.contains(invoice));
    }

}
