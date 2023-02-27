package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleDTO {

    private String name;

    private LocalDate date;

    private Double amount;

    public SaleDTO(String name, LocalDate date, Double amount) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public SaleDTO(Sale entity) {
        name = entity.getSeller().getName();
        date = entity.getDate();
        amount = entity.getAmount();
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }
}
