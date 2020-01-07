package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.mapper.StreetMapper;
import com.house.pojo.Street;
import com.house.pojo.StreetExample;
import com.house.service.StreetService;
import com.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HP on 2019/12/20.
 */
@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo getStreetByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        StreetExample streetExample = new StreetExample();
        List<Street> streetList = streetMapper.selectByExample(streetExample);
        return new PageInfo(streetList);
    }

    @Override
    public int addStreet(Street street) {
        return streetMapper.insert(street);
    }

    @Override
    public Street updataStreetById(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updataStreet(Street street) {
        return streetMapper.updateByPrimaryKeySelective(street);
    }

    @Override
    public int delStreet(Integer id) {
        return streetMapper.deleteByPrimaryKey(id);
    }

    @Override//根据区域ID查询所有街道信息
    public List<Street> getStreetByDid(Integer did) {
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        return streetMapper.selectByExample(streetExample);
    }
}
