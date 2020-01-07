package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.pojo.Users;
import com.house.utils.Page;

import java.util.List;

/**
 * Created by HP on 2019/12/23.
 */
public interface UsersDataService {
    //区域查询分页
    PageInfo selectAllUsersData(Page page);
    //添加区域
    int addUsersData(Users users);
    //根据id获取区域信息   回显
    Users getUsersById(Integer id);
    //修改区域
    int updataUsers(Users users);
    //删除区域
    int deleteUsers(Integer id);
    //批量删除
    int delMoreUsers(Integer[] ids);

    /**
     * 查询姓名是否可用
     * @param name:姓名
     * @return:boolean
     */
    boolean checkName(String name);

    /**
     * 注册用户
     * @param users:用户
     * @return:boolean
     */
    int addUsers(Users users);

    /**
     * 登陆验证功能
     * @param name:用户名
     * @param password:密码
     * @return:用户名对象信息,方便后面属性的使用
     */
    Users loginUsers(String name, String password);
}
