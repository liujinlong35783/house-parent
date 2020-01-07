package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.pojo.Street;
import com.house.utils.Page;

import java.util.List;

/**
 * Created by HP on 2019/12/20.
 */
public interface StreetService {
    //街道查询分页
    PageInfo getStreetByPage(Page page);
    //添加街道信息
    int addStreet(Street street);
    //根据ID查询信息   回显
    Street updataStreetById(Integer id);
    //更新
    int updataStreet(Street street);
    //删除
    int delStreet(Integer id);
    //根据区域ID查询所有街道信息
    List<Street> getStreetByDid(Integer did);
}
