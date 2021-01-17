package service.impl;

import domain.Account;
import mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AccountService;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    //通过Spring整合mybatis配置 可以直接注入
    @Autowired
    private AccountMapper accountMapper;

    public void save(Account account) {
        accountMapper.save(account);
    }

    public List<Account> findAll() {
        return accountMapper.findAll();
    }
}
