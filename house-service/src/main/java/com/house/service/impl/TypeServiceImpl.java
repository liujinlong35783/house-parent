package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.mapper.TypeMapper;
import com.house.pojo.Type;
import com.house.pojo.TypeExample;
import com.house.service.TypeService;
import com.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HP on 2019/12/20.
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public PageInfo getTypeByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        TypeExample typeExample = new TypeExample();
        List<Type> TypeList = typeMapper.selectByExample(typeExample);
        return new PageInfo(TypeList);
    }

    @Override
    public int addType(Type type) {
        return typeMapper.insert(type);
    }

    @Override
    public Type updataTypeById(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updataType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public int delType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override //获取所有类型
    public List<Type> getAllType() {
        TypeExample typeExample = new TypeExample();
        List<Type> typeList = typeMapper.selectByExample(typeExample);
        return typeList;
    }
}
