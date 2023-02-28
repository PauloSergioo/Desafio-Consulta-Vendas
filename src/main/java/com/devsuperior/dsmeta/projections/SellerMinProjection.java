package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface SellerMinProjection {

    Long getId();
    LocalDate getDate();
    Double getAmount();
    String getName();
}