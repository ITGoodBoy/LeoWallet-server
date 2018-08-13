package com.celestialapps.leowallet.controller;

import com.celestialapps.leowallet.model.user.token.Token;
import com.celestialapps.leowallet.request.SignInRequest;
import com.celestialapps.leowallet.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PutMapping(value = "/login")
    public ResponseEntity<Token> login(@RequestBody SignInRequest signInRequest) throws ServiceException {
        return ResponseEntity.ok(userService.login(signInRequest));
    }

}
