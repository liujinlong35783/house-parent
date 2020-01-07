package com.house.mapper;

import com.house.pojo.House;
import com.house.pojo.HouseExample;
import com.house.utils.SearchSome;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    /**
     * 查询房东下面所有的住房信息
     * @param id 房东id
     * @return
     */
    List<House> getAllHouseById(Integer id);

    /**
     * 查询对应房子的信息 用于修改时数据的回显
     * @param id 对应房子的Id
     * @return 房子的所有信息
     */
    House getHouseById(String id);

    /**
     * 有问题:待解决
     * 实现出租房的删除(不删除数据出,更改逻辑字段)
     * @param id 对应房东下房子的id
     * @param isdel 是否删除的逻辑字段
     * @return 影响的行数
     */
    int delHouseById(@Param("id") String id, @Param("isdel") Integer isdel);

    /**
     * 查询所有住房信息
     * @return
     */
    List<House> getAllBackHouse();

    /**
     * 查询所有游客浏览租房信息(已审核),条件查询
     * @return 所有满足条件的房屋信息
     */
    List<House> getAllHouseWithList(SearchSome searchSome);
//    List<House> getAllHouseWithList();
}