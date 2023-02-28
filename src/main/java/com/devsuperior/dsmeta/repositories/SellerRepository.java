package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SellerRepository extends JpaRepository<Sale, Long> {
    @Query(nativeQuery = true, value = "SELECT tb_seller.name, SUM(amount) AS total " +
            "FROM tb_sales \n" +
            "INNER JOIN tb_seller ON tb_seller.id = tb_sales.seller_id " +
            "WHERE date BETWEEN '2022-01-01' AND '2022-12-31' " +
            "GROUP BY tb_seller.name " +
            "HAVING UPPER(tb_seller.name) LIKE  UPPER(CONCAT('%', :name, '%'))")
    List<SaleMinProjection> searchSaleReport(LocalDate min, LocalDate max, String name);
}
