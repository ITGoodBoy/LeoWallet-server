package com.celestialapps.leowallet.model.user.token;


import com.celestialapps.leowallet.constants.HeaderConstants;
import com.celestialapps.leowallet.model.user.User;
import com.celestialapps.leowallet.model.user.UserAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenAuthenticationFilter extends GenericFilterBean {

    private final TokenService tokenService;

    public TokenAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;

        //extract token from header
        final String accessToken = httpRequest.getHeader(HeaderConstants.HEADER_TOKEN);

        if (accessToken != null) {
            Authentication authentication = parseToken(accessToken);

            System.out.println("Authentication: " + authentication);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        chain.doFilter(request, response);
    }

    private Authentication parseToken(String tokenString) {
        try {
            User user = tokenService.getUserByTokenString(tokenString);
            return new UserAuthentication(user);
        } catch (Exception e) {
            return null;
        }
    }

}
