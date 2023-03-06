package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import com.devsuperior.dsmeta.projections.SellerMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(nativeQuery = true, value = "SELECT tb_sales.date, tb_sales.amount, tb_seller.name " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller ON tb_seller.id = tb_sales.seller_id " +
            "WHERE date BETWEEN :min AND :max AND UPPER(tb_seller.name)  LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SellerMinProjection> searchSellerReport(LocalDate min, LocalDate max, String name, Pageable pageable);
}






