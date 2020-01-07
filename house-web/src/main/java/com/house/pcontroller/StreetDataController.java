package com.house.pcontroller;

import com.house.pojo.Street;
import com.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by HP on 2019/12/27.
 */
@RestController(value = "streetController2")
@RequestMapping("/page/")
public class StreetDataController {
    @Autowired
    private StreetService streetService;
    @RequestMapping("getStreetDataByDid")
    public List<Street> getStreetDataByDid(Integer did){
        List<Street> streetList = streetService.getStreetByDid(did);
        return streetList;
    }
}
