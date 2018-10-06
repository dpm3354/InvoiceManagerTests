package com.beaufortfairmont.invoicemanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.openqa.selenium.WebElement;
import org.springframework.boot.json.JsonParser;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.beaufortfairmont.invoicemanager.pages.Page.extractText;
import static org.openqa.selenium.By.className;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Invoice {

    @JsonProperty("invoiceNo")
    private String invoiceNumber;

    private String companyName, typeOfWork;

    @JsonProperty("comment")
    private String description;
    private Status status;

    private LocalDate dueDate;
    private BigDecimal price;


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
