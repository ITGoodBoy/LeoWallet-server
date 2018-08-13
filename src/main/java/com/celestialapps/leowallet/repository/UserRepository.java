package com.celestialapps.leowallet.repository;

import com.celestialapps.leowallet.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {


    boolean existsByAccount(String account);

    User findByAccount(String account);
}
