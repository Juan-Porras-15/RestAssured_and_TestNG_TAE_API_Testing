package com.globant.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {
    private Long id;
    private Long petId;
    private Integer quantity;
    private String shipDate;
    private String status; // "placed", "approved", "delivered"
    private Boolean complete;

    public static OrderDTO generateFake(Long petId) {
        return OrderDTO.builder()
                .id(System.nanoTime())
                .petId(petId)
                .quantity(1)
                .shipDate("2026-03-20T12:00:00.000Z")
                .status("placed")
                .complete(false)
                .build();
    }
}