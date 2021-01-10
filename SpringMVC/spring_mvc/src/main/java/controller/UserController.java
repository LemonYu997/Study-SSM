package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//控制器类
@Controller
@RequestMapping("/user")
public class UserController {

    //请求地址 http://localhost:8080/user/quick?username=xxx
    //请求映射
    @RequestMapping(value = "/quick", method = RequestMethod.GET, params = {"username"})
    public String save() {
        System.out.println("Controller save running...");
        return "success";   //默认为jsp的名字
    }
}
