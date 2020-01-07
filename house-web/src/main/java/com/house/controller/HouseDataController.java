package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.pojo.House;
import com.house.service.HouseService;
import com.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2019/12/31.
 */
@RestController
@RequestMapping("/admin/")
public class HouseDataController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("getHouseData")
    public Map<String,Object> getHouseData(Page page){
        PageInfo<House> pageInfo = houseService.getAllHouse(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        System.out.println("page:"+page.getPage());
        return map;
    }
    @RequestMapping("updateHouseIspass")
    public Map<String,Object> updateHouseIspass(String id){
        int result = houseService.updataHouseIspass(id,1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result",result);
        return map;
    }
}
