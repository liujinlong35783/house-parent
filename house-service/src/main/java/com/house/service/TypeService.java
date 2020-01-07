package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.pojo.Type;
import com.house.utils.Page;

import java.util.List;

/**
 * Created by HP on 2019/12/20.
 */
public interface TypeService {
    //街道查询分页
    PageInfo getTypeByPage(Page page);
    //添加街道信息
    int addType(Type type);
    //根据ID查询信息   回显
    Type updataTypeById(Integer id);
    //更新
    int updataType(Type type);
    //删除
    int delType(Integer id);
    //获取所有类型
    List<Type> getAllType();
    //导出类型

}
