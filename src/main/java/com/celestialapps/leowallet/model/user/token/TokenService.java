package com.celestialapps.leowallet.model.user.token;


import com.celestialapps.leowallet.model.user.User;

public interface TokenService {


    Token generateToken(User user);
    User getUserByTokenString(String tokenString);
    boolean deleteByTokenString(String tokenStrin);
    Token getTokenByTokenString(String tokenString);
}
