package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.mapper.HouseMapper;
import com.house.pojo.House;
import com.house.pojo.HouseExample;
import com.house.service.HouseService;
import com.house.utils.Page;
import com.house.utils.SearchSome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HP on 2019/12/27.
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;


    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> findAllHouseById(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> houseList = houseMapper.getAllHouseById(id);
        return new PageInfo<>(houseList);
    }

    @Override
    public House getHouseById(String id) {
        return houseMapper.getHouseById(id);
    }

    @Override
    public int updataHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int delHouseById(String id, Integer isdel) {
        House house = new House();
        house.setId(id);
        house.setIsdel(1);
//        return houseMapper.delHouseById(id,isdel); 有问题待解决
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getAllHouse(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
//      List<House> houses = houseMapper.selectByExample(new HouseExample());
        List<House> houses = houseMapper.getAllBackHouse();
        PageInfo<House> pageInfo = new PageInfo<>(houses);
        return pageInfo;
    }

    @Override
    public int updataHouseIspass(String id,Integer ispass) {
        House house = new House();
        house.setId(id);
        house.setIspass(1);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getAllHouseWithList(SearchSome searchSome) {
        PageHelper.startPage(searchSome.getPage(),searchSome.getRows());
        List<House> houseList = houseMapper.getAllHouseWithList(searchSome);
//        List<House> houseList = houseMapper.getAllHouseWithList();
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return pageInfo;
    }
}
