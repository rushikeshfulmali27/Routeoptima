package com.routeoptima.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private Long shipmentId;
    private Double amount;
    private LocalDateTime paymentDate;
}
