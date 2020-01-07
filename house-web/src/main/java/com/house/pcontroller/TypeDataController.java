package com.house.pcontroller;

import com.house.pojo.Type;
import com.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by HP on 2019/12/27.
 */
@Controller(value = "typeController2")
@RequestMapping("/page/")
public class TypeDataController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("getTypeData")
    @ResponseBody
    public List<Type> getTypeData(){
        List<Type> typeList = typeService.getAllType();
        return typeList;
    }
}
