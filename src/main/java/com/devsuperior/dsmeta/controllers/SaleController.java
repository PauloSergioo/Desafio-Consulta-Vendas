package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SellerDTO;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SellerDTO>> getReport(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(name = "name", defaultValue = "") String name,
            Pageable pageable
    )
    {
        Page<SellerDTO> dto = service.sellerReport(minDate, maxDate,name, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SaleDTO>> getSummary(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
    )
    {
        List<SaleDTO> dto = service.salesSummary(minDate, maxDate);
        return ResponseEntity.ok(dto);
    }
}
