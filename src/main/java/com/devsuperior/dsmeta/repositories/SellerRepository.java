package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projections.SellerMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query(nativeQuery = true, value = "SELECT tb_sales.date, tb_sales.amount, tb_seller.name " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller ON tb_seller.id = tb_sales.seller_id " +
            "WHERE date BETWEEN :min AND :max AND UPPER(tb_seller.name)  LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SellerMinProjection> searchSellerReport(LocalDate min, LocalDate max, String name, Pageable pageable);
}
