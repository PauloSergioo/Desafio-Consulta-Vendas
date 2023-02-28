package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface SellerMinProjection {

    Long getId();
    String getName();
    LocalDate getDate();
    Double getAmount();
}