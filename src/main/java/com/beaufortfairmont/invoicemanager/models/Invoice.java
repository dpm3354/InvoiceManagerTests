package com.beaufortfairmont.invoicemanager.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Invoice {
    private String invoiceNumber, companyName, typeOfWork, description;
    private Status status;
    private LocalDate dueDate;
    private BigDecimal price;


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
