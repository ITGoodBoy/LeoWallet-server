package com.celestialapps.leowallet.impl;

import com.celestialapps.leowallet.model.PromotionalCode;
import com.celestialapps.leowallet.model.Transaction;
import com.celestialapps.leowallet.model.user.User;
import com.celestialapps.leowallet.model.user.token.Token;
import com.celestialapps.leowallet.model.user.token.TokenService;
import com.celestialapps.leowallet.repository.PromotionalCodeRepository;
import com.celestialapps.leowallet.repository.TransactionRepository;
import com.celestialapps.leowallet.repository.UserRepository;
import com.celestialapps.leowallet.request.*;
import com.celestialapps.leowallet.response.Balance;
import com.celestialapps.leowallet.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private TokenService tokenService;
    private PasswordEncoder passwordEncoder;
    private TransactionRepository transactionRepository;
    private PromotionalCodeRepository promotionalCodeRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenService tokenService,
                           PasswordEncoder passwordEncoder, TransactionRepository transactionRepository,
                           PromotionalCodeRepository promotionalCodeRepository) {

        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.transactionRepository = transactionRepository;
        this.promotionalCodeRepository = promotionalCodeRepository;
    }

    @Override
    public Token login(SignInRequest signInRequest) throws ServiceException {
        String account = signInRequest.getAccount();
        String password = signInRequest.getPassword();

        if (!userRepository.existsByAccount(account)) {
            throw new ServiceException("Пользователь с данным кошельком не зарегестрирован");
        }

        User user = userRepository.findByAccount(account);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ServiceException("Неправельный пароль");
        }

        return tokenService.generateToken(user);
    }

    @Transactional
    @Override
    public Balance upBalance(User user, UpBalanceRequest upBalanceRequest) throws ServiceException {
        long balanceUp = upBalanceRequest.getBalanceUp();

        if (balanceUp <= 0) throw new ServiceException("Нельзя пополнить баланс на 0 или на отрицательное число");

        user.setBalance(user.getBalance() + balanceUp);

        Transaction transaction = new Transaction(balanceUp, user.getAccount(), user.getAccount());
        transaction = transactionRepository.save(transaction);

        user.getTransactions().add(transaction);

        User u = userRepository.save(user);

        return new Balance(u.getBalance());
    }

    @Transactional
    @Override
    public Balance sendMoney(User myAccount, SendMoneyRequest sendMoneyRequest) throws ServiceException {
        String accountFrom = myAccount.getAccount();
        String accountTo = sendMoneyRequest.getAccountTo();
        long moneyCount = sendMoneyRequest.getMoneyCount();

        if (moneyCount <= 0) {
            throw new ServiceException("Нельзя пополнить счёт на 0 или на отрицательное число");
        }

        if (myAccount.getBalance() < moneyCount) {
            throw new ServiceException("Вы пополняете на суму большую чем у вас на счету");
        }

        if (!userRepository.existsByAccount(accountTo)) {
            throw new ServiceException("Пользователь с данным кошельком не зарегестрирован");
        }

        User user = userRepository.findByAccount(accountTo);

        myAccount.setBalance(myAccount.getBalance() - moneyCount);
        user.setBalance(user.getBalance() + moneyCount);

        Transaction transaction = new Transaction(moneyCount, accountFrom, accountTo);
        transaction = transactionRepository.save(transaction);

        Transaction transaction2 = new Transaction(moneyCount, accountFrom, accountTo);
        transaction2 = transactionRepository.save(transaction2);

        myAccount.getTransactions().add(transaction);
        user.getTransactions().add(transaction2);

        userRepository.save(myAccount);
        userRepository.save(user);


        return new Balance(myAccount.getBalance());
    }

    @Transactional
    @Override
    public Balance receiveMoney(User myAccount, ReceiveMoneyRequest receiveMoneyRequest) throws ServiceException {
        long moneyCount = receiveMoneyRequest.getMoneyCount();

        if (receiveMoneyRequest.getMoneyCount() > moneyCount) {
            throw new ServiceException("Нельзя снять больше чем у вас на счету");
        }

        myAccount.setBalance(myAccount.getBalance() - moneyCount);

        Transaction transaction = new Transaction(moneyCount, myAccount.getAccount(), myAccount.getAccount());
        transaction = transactionRepository.save(transaction);

        myAccount.getTransactions().add(transaction);
        User u = userRepository.save(myAccount);

        return new Balance(u.getBalance());
    }

    @Override
    public Balance enterPromotionalCode(User myAccount, PromotionalCodeRequest promotionalCodeRequest) throws ServiceException {
        if (!promotionalCodeRepository.existsByCode(promotionalCodeRequest.getPromotionalCode())) {
            throw new ServiceException("Промокод не действителен или уже использован");
        }

        PromotionalCode promotionalCode = promotionalCodeRepository.findByCode(promotionalCodeRequest.getPromotionalCode());

        myAccount.setBalance(myAccount.getBalance() + promotionalCode.getMoneyCount());

        promotionalCodeRepository.delete(promotionalCode);
        User u = userRepository.save(myAccount);

        return new Balance(u.getBalance());
    }

    @Override
    public List<Transaction> getMyTransactions(User user) {
        return user.getTransactions();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        return userRepository.findByAccount(account);
    }
}
