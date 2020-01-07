package com.house.pcontroller;

import com.house.pojo.District;
import com.house.service.DistrictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by HP on 2019/12/27.
 */
@Controller(value = "districtController2")
@RequestMapping("/page/")
public class DistrictDataController {
    @Autowired
    private DistrictDataService districtDataService;
    @RequestMapping("getDistrictData")
    @ResponseBody
    public List<District> getDistrictData(){
        List<District> districtList = districtDataService.getAllDistrict();
        return districtList;
    }
}
