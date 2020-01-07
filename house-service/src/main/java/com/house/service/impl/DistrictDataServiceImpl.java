package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.mapper.DistrictMapper;
import com.house.pojo.District;
import com.house.pojo.DistrictExample;
import com.house.service.DistrictDataService;
import com.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HP on 2019/12/23.
 */
@Service
public class DistrictDataServiceImpl implements DistrictDataService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public PageInfo selectAllDistrictData(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        DistrictExample districtExample = new DistrictExample();
        List<District> districts = districtMapper.selectByExample(districtExample);
        return new PageInfo(districts);
    }

    @Override
    public int addDistrictData(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public District getDistrictById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updataDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    public int deleteDistrict(Integer id) {
        return districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreDistrict(Integer[] ids) {
        return districtMapper.delMoreDistrict(ids);
    }

    @Override//查询所有区域
    public List<District> getAllDistrict() {
        DistrictExample districtExample = new DistrictExample();
        List<District> districtList = districtMapper.selectByExample(districtExample);
        return districtList;
    }
}
