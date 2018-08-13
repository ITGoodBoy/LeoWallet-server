package com.celestialapps.leowallet.model.user.token;

import com.celestialapps.leowallet.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Token findByTokenString(String token);
    Token findByUser(User user);

    boolean existsByUser(User user);
    boolean existsByTokenString(String tokenSting);
    boolean deleteByTokenString(String tokenString);
}
