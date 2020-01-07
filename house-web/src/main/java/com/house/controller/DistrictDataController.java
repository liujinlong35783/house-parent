package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.pojo.District;
import com.house.service.DistrictDataService;
import com.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HP on 2019/12/23.
 */
@RestController
@RequestMapping("/admin/")
public class DistrictDataController {
    @Autowired
    private DistrictDataService districtDataService;
    @RequestMapping("getDistrictData")
    public Map<String,Object> getDistrictData(Page page){
        PageInfo pageInfo = districtDataService.selectAllDistrictData(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("addDistrictData")
    public Map<String,Object> addDistrictData(District district){
        int result = districtDataService.addDistrictData(district);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result",result);
        return map;
    }
    @RequestMapping("getDistrictDataById")//回显
    public District getDistrictDataById(Integer id){
        District district = districtDataService.getDistrictById(id);
        return district;
    }
    @RequestMapping("updataDistrict")
    public String updataDistrict(District district){
        int result = districtDataService.updataDistrict(district);
        return "{\"result\":"+result+"}";
    }
    @RequestMapping("delDistrict")
    public String delDistrict(Integer id){
        int result = districtDataService.deleteDistrict(id);
        return "{\"result\":"+result+"}";
    }
    @RequestMapping("delMoreDistrict")
    public String delMoreDistrict(String ids){
        //将字符串转化为数据组
//        public String delMoreDistrict(String ids){
        String [] strList=ids.split(",");
        Integer [] idList=new Integer[strList.length];
        for (int i=0;i<strList.length;i++) {
            idList[i]=new Integer(strList[i]);
        }
        int result = districtDataService.delMoreDistrict(idList);
        return "{\"result\":"+result+"}";
    }
}
