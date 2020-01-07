package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.pojo.Users;
import com.house.service.UsersDataService;
import com.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2019/12/23.
 */
@RestController
@RequestMapping("/admin/")
public class UsersDataController {
    @Autowired
    private UsersDataService usersDataService;
    @RequestMapping("getUsersData")
    public Map<String,Object> getUsersData(Page page){
        PageInfo pageInfo = usersDataService.selectAllUsersData(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("addUsersData")
    public Map<String,Object> addUsersData(Users users){
        int result = usersDataService.addUsersData(users);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result",result);
        return map;
    }
    @RequestMapping("getUsersDataById")//回显
    public Users getUsersDataById(Integer id){
        Users users = usersDataService.getUsersById(id);
        return users;
    }
    @RequestMapping("updataUsers")
    public String updataUsers(Users users){
        int result = usersDataService.updataUsers(users);
        return "{\"result\":"+result+"}";
    }
    @RequestMapping("delUsers")
    public String delUsers(Integer id){
        int result = usersDataService.deleteUsers(id);
        return "{\"result\":"+result+"}";
    }
    @RequestMapping("delMoreUsers")
    public String delMoreUsers(String ids){
        //将字符串转化为数据组
//        public String delMoreUsers(String ids){
        String [] strList=ids.split(",");
        Integer [] idList=new Integer[strList.length];
        for (int i=0;i<strList.length;i++) {
            idList[i]=new Integer(strList[i]);
        }
        int result = usersDataService.delMoreUsers(idList);
        return "{\"result\":"+result+"}";
    }
}
