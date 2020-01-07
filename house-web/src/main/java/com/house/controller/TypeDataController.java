package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.pojo.Type;
import com.house.service.TypeService;
import com.house.utils.Page;
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
public class TypeDataController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("getTypeByPage")
    @ResponseBody
    public Map<String,Object> getTypeByPage(Page page){
        PageInfo type = typeService.getTypeByPage(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",type.getTotal());
        map.put("rows",type.getList());
        System.out.println("type"+type);
        return map;
    }
    @RequestMapping("addType")
    @ResponseBody
    public Map<String,Object> addType(Type type){
        int b = typeService.addType(type);
        //使用map封装返回的数据
        // return "{\"result\":"+flag+"}";  //手工拼的json
        HashMap<String, Object> map = new HashMap<>();
        map.put("b",b);
        return map;
    }
    //更新时用来做为回显的数据
    @RequestMapping("updataTypeUI")
    @ResponseBody
    public Type updataTypeUI(Integer id){
        Type type = typeService.updataTypeById(id);
        return type;
    }
    @RequestMapping("updataType")
    @ResponseBody
    public String updataType(Type type){
        int result = typeService.updataType(type);
        return "{\"result\":"+result+"}";
    }
    @RequestMapping("delType")
    @ResponseBody
    public String delType(Integer id){
        int result = typeService.delType(id);
        return "{\"result\":"+result+"}";
    }
}
