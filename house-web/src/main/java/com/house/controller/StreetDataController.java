package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.pojo.Street;
import com.house.service.StreetService;
import com.house.utils.Page;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2019/12/20.
 */

@Controller
@RequestMapping("/admin/")
public class StreetDataController {
    @Autowired
    private StreetService streetService;
    @RequestMapping("getStreetByPage")
    @ResponseBody
    public Map<String,Object> getStreetByPage(Page page){
        PageInfo street = streetService.getStreetByPage(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",street.getTotal());
        map.put("rows",street.getList());
        System.out.println("street"+street);
        return map;
    }
    @RequestMapping("addStreet")
    @ResponseBody
    public Map<String,Object> addStreet(Street street){
        int b = streetService.addStreet(street);
        //使用map封装返回的数据
        // return "{\"result\":"+flag+"}";  //手工拼的json
        HashMap<String, Object> map = new HashMap<>();
        map.put("b",b);
        return map;
    }
    //更新时用来做为回显的数据
    @RequestMapping("updataStreetUI")
    @ResponseBody
    public Street updataStreetUI(Integer id){
        Street street = streetService.updataStreetById(id);
        return street;
    }
    @RequestMapping("updataStreet")
    @ResponseBody
    public String updataStreet(Street street){
        int result = streetService.updataStreet(street);
        return "{\"result\":"+result+"}";
    }
    @RequestMapping("delStreet")
    @ResponseBody
    public String delStreet(Integer id){
        int result = streetService.delStreet(id);
        return "{\"result\":"+result+"}";
    }
}
