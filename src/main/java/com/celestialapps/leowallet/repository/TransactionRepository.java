package com.celestialapps.leowallet.repository;

import com.celestialapps.leowallet.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
