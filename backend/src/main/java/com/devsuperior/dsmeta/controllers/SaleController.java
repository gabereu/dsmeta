package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositiories.SaleRepository;
import com.devsuperior.dsmeta.services.MessageService;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {
    
    @Autowired
    private SaleService saleService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SaleRepository saleRepository;

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

    @GetMapping("{id}/notification")
    public void notificate(@PathVariable Long id){

        var saleSearch = saleRepository.findById(id);

        if(saleSearch.isEmpty()){
            throw new RuntimeException("Sale not founded");
        }

        Sale sale = saleSearch.get();

        String date = sale.getDate().getMonth() + "/" + sale.getDate().getYear();

        String message = "O vendedor " + sale.getSellerName() + " em " + date + " vendeu R$" + String.format("%.2f", sale.getAmount());

        messageService.sendMessage("person@mail.com", "Notificação", message);

    }

}
