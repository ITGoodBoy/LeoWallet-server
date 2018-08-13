package com.celestialapps.leowallet.repository;

import com.celestialapps.leowallet.model.PromotionalCode;
import org.springframework.data.repository.CrudRepository;

public interface PromotionalCodeRepository extends CrudRepository<PromotionalCode, Long> {



    boolean existsByCode(String code);
    PromotionalCode findByCode(String code);
}
