package com.beaufortfairmont.invoicemanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.util.resources.LocaleData;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private String invoiceNumber, companyName, typeOfWork, description;
    private Status status;
    private LocalDate dueDate;
    private BigDecimal price;



    public enum Status {
        PAID, SENT, DRAFT, PAST_DUE;
    }
}
