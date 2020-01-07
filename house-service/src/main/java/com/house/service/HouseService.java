package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.pojo.House;
import com.house.utils.Page;
import com.house.utils.SearchSome;

import java.util.List;

/**
 * Created by HP on 2019/12/27.
 */
public interface HouseService {
    //发布租房信息
    int addHouse(House house);
    /**
     * 查询房东下面所有的住房信息
     * @param id 房东id
     * @return
     */
    PageInfo<House> findAllHouseById(Integer id, Page page);

    /**
     * 查询对应id房子的信息 回显
     * @param id 房子的id
     * @return 返回对应房子的信息
     */
    House getHouseById(String id);

    /**
     * 更新房屋信息
     * @param hosue 房屋信息
     * @return
     */
    int updataHouse(House hosue);
    /**
     * 实现出租房的删除(不删除数据出,更改逻辑字段)
     * @param id 对应房东下房子的id
     * @param isdel 是否删除的逻辑字段
     * @return 影响的行数
     */
    int delHouseById(String id, Integer isdel);

    /**
     * 查询所有住房信息
     * @param page 分页工具类
     * @return
     */
    PageInfo<House> getAllHouse(Page page);

    /**
     * 审核房屋信息
     * @param ispass 审核的字段
     * @return
     */
    int updataHouseIspass(String id, Integer ispass);
    /**
     * 查询所有游客浏览租房信息(已审核),条件查询
     * @return 所有满足条件的房屋信息
     */
    PageInfo<House> getAllHouseWithList(SearchSome searchSome);
}
