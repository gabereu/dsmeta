package com.devsuperior.dsmeta.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositiories.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public Page<Sale> findSales(Pageable pageable){
        return saleRepository.findAll(pageable);
    };

    public Page<Sale> findSales(LocalDate minDate, LocalDate maxDate, Pageable pageable){
        return saleRepository.findSalesOrderByAmountDesc(minDate, maxDate, pageable);
    };

    public Page<Sale> findSalesAfterDate(LocalDate date, Pageable pageable){
        return saleRepository.findAllWithDateAfterOrderByAmountDesc(date, pageable);
    };

    public Page<Sale> findSalesBeforeDate(LocalDate date, Pageable pageable){
        return saleRepository.findAllWithDateAfterOrderByAmountDesc(date, pageable);
    };


}
