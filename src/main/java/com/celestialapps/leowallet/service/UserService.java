package com.celestialapps.leowallet.service;

import com.celestialapps.leowallet.model.Transaction;
import com.celestialapps.leowallet.model.user.User;
import com.celestialapps.leowallet.model.user.token.Token;
import com.celestialapps.leowallet.request.*;
import com.celestialapps.leowallet.response.Balance;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    Token login(SignInRequest signInRequest) throws ServiceException;

    Balance upBalance(User user, UpBalanceRequest upBalanceRequest) throws ServiceException;
    Balance sendMoney(User myAccount, SendMoneyRequest sendMoneyRequest) throws ServiceException;
    Balance receiveMoney(User myAccount, ReceiveMoneyRequest receiveMoneyRequest) throws ServiceException;
    Balance enterPromotionalCode(User myAccount, PromotionalCodeRequest promotionalCodeRequest) throws ServiceException;

    List<Transaction> getMyTransactions(User user);
    User findById(long id);
    User getLoggedUser();
}
