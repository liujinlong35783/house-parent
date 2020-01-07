package com.house.pcontroller;

import com.house.mapper.UsersMapper;
import com.house.pojo.Users;
import com.house.service.UsersDataService;
import com.house.utils.MsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HP on 2019/12/26.
 */
@Controller(value = "usersController2")//名字不一样是不需要,一样时 添加Value
@RequestMapping("/page/")
public class UsersDataController {
    @Autowired
    private UsersDataService usersDataService;

    @RequestMapping("checkName")
    @ResponseBody
    public String checkName(String name){
        boolean result = usersDataService.checkName(name);
        return "{\"result\":"+result+"}";
    }
    @RequestMapping("addUsers")
    public String addUsers(Users users){
        int i = usersDataService.addUsers(users);
        if (i>0)
            return "redirect:login.jsp";
        else
            return "error.html";
    }
    @RequestMapping("loginUsers")
    public String loginUsers(String name, String password, Model model, HttpSession session){
        Users users = usersDataService.loginUsers(name, password);
        if(users!=null){
            session.setAttribute("users",users);
            System.out.println("users:"+users);
            return "redirect:/page/getAllHouseDataById";
        }else{
            model.addAttribute("login","fail");
            return "page/login";
        }
    }
    @RequestMapping("loginUsersByTel")
    @ResponseBody
    public String loginUsersByTel(String tel,HttpSession session){
        MsgCode msgCode = new MsgCode();
        Integer code= (int)(Math.random()*(9999-1000+1)+1000);
        int result = msgCode.sendMsg(tel, code.toString());
        session.setAttribute("code",code);
        System.out.println("result:"+result);
        return "{\"result\":"+result+"}";
    }
}

