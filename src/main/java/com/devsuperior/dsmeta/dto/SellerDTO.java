package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleMinProjection;
import com.devsuperior.dsmeta.projections.SellerMinProjection;

import java.time.LocalDate;

public class SellerDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String name;

    public SellerDTO(Long id, LocalDate date, Double amount, String name) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.name = name;
    }

    public SellerDTO(SellerMinProjection entity) {
        amount = entity.getAmount();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
