package com.beaufortfairmont.invoicemanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static com.beaufortfairmont.invoicemanager.pages.Page.extractText;
import static org.openqa.selenium.By.className;

public class Invoice {

    @JsonProperty("invoiceNo")
    private String invoiceNumber;

    private String companyName;
    private String typeOfWork;

    @JsonProperty("comment")
    private String description;
    private Status status;

    private LocalDate dueDate;
    private BigDecimal price;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public static class Builder {

        private final Invoice invoice;

        private Builder() {
            this.invoice = new Invoice();
        }

        public Builder invoiceNumber(String invoiceNumber) {
            invoice.invoiceNumber = invoiceNumber;
            return this;
        }

        public Builder companyName(String companyName) {
            invoice.companyName = companyName;
            return this;
        }

        public Builder typeOfWork(String typeOfWork) {
            invoice.typeOfWork = typeOfWork;
            return this;
        }

        public Builder dueDate(LocalDate dueDate) {
            invoice.dueDate = dueDate;
            return this;
        }

        public Builder price(BigDecimal price) {
            invoice.price = price;
            return this;
        }

        public Builder description(String description) {
            invoice.description = description;
            return this;
        }

        public Builder status(Status status) {
            invoice.status = status;
            return this;
        }

        public Invoice build() {
            return invoice;
        }
    }

    public static Builder builder() {
        return new Builder();
    }


    public static Invoice from(WebElement element) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(getInvoiceNumber(), invoice.getInvoiceNumber()) &&
                Objects.equals(getCompanyName(), invoice.getCompanyName()) &&
                Objects.equals(getTypeOfWork(), invoice.getTypeOfWork()) &&
                Objects.equals(getDescription(), invoice.getDescription()) &&
                getStatus() == invoice.getStatus() &&
                Objects.equals(getDueDate(), invoice.getDueDate()) &&
                Objects.equals(getPrice(), invoice.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceNumber(), getCompanyName(), getTypeOfWork(), getDescription(), getStatus(), getDueDate(), getPrice());
    }


    public enum Status {
        PAID, SENT, DRAFT, PAST_DUE;

        public static Optional<Status> parse(String status) {
            for (Status value : values()) {
                if (value.toString().replace("_", " ").equalsIgnoreCase(status)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}
