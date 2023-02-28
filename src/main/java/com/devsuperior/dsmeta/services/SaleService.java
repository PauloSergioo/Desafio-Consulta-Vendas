package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SellerDTO;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import com.devsuperior.dsmeta.projections.SellerMinProjection;
import com.devsuperior.dsmeta.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

    @Autowired
    private SaleRepository repository;

    @Autowired
    private SellerRepository repositorySeller;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public List<SaleDTO> salesSummary(String min, String max) {
        LocalDate minResult;
        LocalDate maxResult;
        if (min.equals("")) {
            minResult = today.minusYears(1L);
        } else {
            minResult = LocalDate.parse(min);
        }
        if (max.equals("")) {
            maxResult = today;
        } else {
            maxResult = LocalDate.parse(max);
        }
        List<SaleMinProjection> result = repository.searchSaleSummary(minResult, maxResult);
        return result.stream().map(SaleDTO::new).collect(Collectors.toList());
    }

    public List<SellerDTO> sellerReport(String min, String max, String name) {
        LocalDate minResult;
        LocalDate maxResult;
        if (min.equals("")) {
            minResult = today.minusYears(1L);
        } else {
            minResult = LocalDate.parse(min);
        }
        if (max.equals("")) {
            maxResult = today;
        } else {
            maxResult = LocalDate.parse(max);
        }
        List<SellerMinProjection> result = repositorySeller.searchSellerReport(minResult, maxResult, name);
        return result.stream().map(SellerDTO::new).collect(Collectors.toList());
    }
}