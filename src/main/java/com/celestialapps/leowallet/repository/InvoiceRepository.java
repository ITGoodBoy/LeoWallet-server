package com.celestialapps.leowallet.repository;

import com.celestialapps.leowallet.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
