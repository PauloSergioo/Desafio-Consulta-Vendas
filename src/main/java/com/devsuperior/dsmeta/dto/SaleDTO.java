package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleMinProjection;

public class SaleDTO {

    private String name;
    private Double amount;

    public SaleDTO(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public SaleDTO(SaleMinProjection entity) {
        name = entity.getName();
        amount = entity.getAmount();
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }
}
