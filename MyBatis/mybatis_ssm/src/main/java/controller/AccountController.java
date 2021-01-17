package controller;

import domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.AccountService;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //保存 设置produces防止响应信息乱码
    @RequestMapping(value = "/save", produces = "text/html;charset=utf-8")
    @ResponseBody   //不进行跳转
    public String save(Account account) {
        accountService.save(account);
        return "保存成功";
    }

    //查询
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        List<Account> accountList = accountService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accountList", accountList);
        modelAndView.setViewName("accountList");
        return modelAndView;
    }
}
