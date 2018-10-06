package com.beaufortfairmont.invoicemanager;

import com.beaufortfairmont.invoicemanager.models.Invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.beaufortfairmont.invoicemanager.models.Invoice.Status.DRAFT;

public class InvoiceFactory {
    public static Invoice getSimpleInvoice() {
        return Invoice
                .builder()
                .companyName("All Day Plumbing")
                .invoiceNumber("10030")
                .typeOfWork("Plumbing")
                .status(DRAFT)
                .price(new BigDecimal("75"))
                .companyName("Installed toilet handle.")
                .description("stanky")
                .dueDate(LocalDate.of(2016,4, 30))
                .build();
    }
}
