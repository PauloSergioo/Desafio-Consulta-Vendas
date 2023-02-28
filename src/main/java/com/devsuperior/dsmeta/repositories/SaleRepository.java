package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SaleMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_seller.name, SUM(amount) AS amount " +
            "FROM tb_sales  " +
            "INNER JOIN tb_seller  ON tb_seller.id = tb_sales.seller_id " +
            "WHERE date BETWEEN :min AND :max " +
            "GROUP BY tb_seller.name")
    List<SaleMinProjection> searchSaleSummary(LocalDate min, LocalDate max);
}