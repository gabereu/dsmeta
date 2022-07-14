package com.devsuperior.dsmeta.repositiories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE s.date BETWEEN :minDate AND :maxDate ORDER BY s.amount DESC")
    Page<Sale> findSalesOrderByAmountDesc(LocalDate minDate, LocalDate maxDate, Pageable pageable);

    @Query("SELECT s FROM Sale s WHERE s.date >= :date ORDER BY s.amount DESC")
    Page<Sale> findAllWithDateAfterOrderByAmountDesc(LocalDate date, Pageable pageable);

    @Query("SELECT s FROM Sale s WHERE s.date <= :date ORDER BY s.amount DESC")
    Page<Sale> findAllWithDateBeforeOrderByAmountDesc(LocalDate date, Pageable pageable);
}