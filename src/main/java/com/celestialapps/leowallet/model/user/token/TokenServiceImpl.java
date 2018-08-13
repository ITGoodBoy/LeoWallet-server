package com.celestialapps.leowallet.model.user.token;

import com.celestialapps.leowallet.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token generateToken(User user) {
        String tokenString = UUID.randomUUID().toString();

        if (tokenRepository.existsByTokenString(tokenString)) {
            generateToken(user);
        }

        Token token;

        if (!tokenRepository.existsByUser(user)) {
            token = new Token(user, tokenString);
        } else {
            token = tokenRepository.findByUser(user);
            token.setCreatedAt(new Date(System.currentTimeMillis()));
            token.setTokenString(tokenString);
        }

        return tokenRepository.save(token);
    }

    @Override
    public User getUserByTokenString(String tokenString) {
        return tokenRepository.findByTokenString(tokenString).getUser();
    }

    @Override
    public boolean deleteByTokenString(String token) {
        return tokenRepository.deleteByTokenString(token);
    }

    @Override
    public Token getTokenByTokenString(String tokenString) {
        return tokenRepository.findByTokenString(tokenString);
    }

}
