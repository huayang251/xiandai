package com.kuang.service;

import com.github.pagehelper.PageInfo;
import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther shkstart
 * @date 2021/5/23 - 16:35
 */
public interface UserService {

    //根据账号密码查询用户
    User queryUserByuserCodeandPassword(String userCode,String password);

    //增加一个用户
    int addUser(User user);

    //删除一个用户
    int deleteUserById(int id);

    //更新一个用户
    int updateUser(User user);

    //根据Id查询一个用户
    User queryUserById(int id);

    //查询全部用户
    List<User> queryAllUser();

    //分页查询全部用户
    PageInfo<User> getUserByPage(int page,int pageSize);

    //根据名字模糊查询
    List<User> queryUserByName(String userName);
}
