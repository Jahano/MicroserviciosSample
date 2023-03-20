package com.microservicios.sample.shoppingservice.repository;


import com.microservicios.sample.shoppingservice.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}