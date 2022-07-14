package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {
    
    @Autowired
    private SaleService saleService;

    @GetMapping
    public Page<Sale> getSales(
        Pageable pageable,
        @RequestParam Optional<String> minDate,
        @RequestParam Optional<String> maxDate
    ){

        if(minDate.isPresent() && maxDate.isPresent()){
            return saleService.findSales(LocalDate.parse(minDate.get()), LocalDate.parse(maxDate.get()), pageable);   
        }

        if(minDate.isPresent()){
            return saleService.findSalesAfterDate(LocalDate.parse(minDate.get()), pageable);
        }

        if(maxDate.isPresent()){
            return saleService.findSalesBeforeDate(LocalDate.parse(maxDate.get()), pageable);
        }

        return saleService.findSales(pageable);
    }

}
