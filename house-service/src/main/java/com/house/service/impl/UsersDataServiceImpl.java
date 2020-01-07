package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.mapper.UsersMapper;
import com.house.pojo.Users;
import com.house.pojo.UsersExample;
import com.house.service.UsersDataService;
import com.house.utils.MD5Utils;
import com.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HP on 2019/12/23.
 */
@Service
public class UsersDataServiceImpl implements UsersDataService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo selectAllUsersData(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        if (page.getName()!=null){
            criteria.andNameLike("%"+page.getName()+"%");
        }
        if(page.getTelephone()!=null) {
            criteria.andTelephoneLike("%" + page.getTelephone() + "%");
        }
        List<Users> userss = usersMapper.selectByExample(usersExample);
        return new PageInfo(userss);
    }

    @Override
    public int addUsersData(Users users) {
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users getUsersById(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updataUsers(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public int deleteUsers(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreUsers(Integer[] ids) {
        return usersMapper.delMoreUsers(ids);
    }

    @Override
    public boolean checkName(String name) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Users> users = usersMapper.selectByExample(usersExample);
        if (users.size()!=0)
            return false;
        else
            return true;
    }

    @Override
    public int addUsers(Users users) {
        users.setIsadmin(0);
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    @Override
    public  Users loginUsers(String name, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        String newpassword=MD5Utils.md5Encrypt(password);
        criteria.andPasswordEqualTo(newpassword);
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        if(usersList!=null&&usersList.size()>0){
            return usersList.get(0);
        }
        return null ;
    }
}
