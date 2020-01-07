package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.pojo.District;
import com.house.utils.Page;

import java.util.List;

/**
 * Created by HP on 2019/12/23.
 */
public interface DistrictDataService {
    //区域查询分页
    PageInfo selectAllDistrictData(Page page);
    //添加区域
    int addDistrictData(District district);
    //根据id获取区域信息   回显
    District getDistrictById(Integer id);
    //修改区域
    int updataDistrict(District district);
    //删除区域
    int deleteDistrict(Integer id);
    //批量删除
    int delMoreDistrict(Integer[] ids);
    //查询所有区域
    List<District> getAllDistrict();
}
