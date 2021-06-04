package com.kuang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuang.dao.UserMapper;
import com.kuang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther shkstart
 * @date 2021/5/23 - 16:36
 */

public class UserServiceImpl implements UserService{

    private UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper){this.userMapper=userMapper;};


    public User queryUserByuserCodeandPassword(String userCode, String password) {
        return userMapper.queryUserByuserCodeandPassword(userCode,password);
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public User queryUserById(int id) {
        return userMapper.queryUserById(id);
    }

    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }


    public PageInfo<User> getUserByPage(int page, int pageSize) {
        //开启分页支持
        PageHelper.startPage(page,pageSize);
        //调用Dao层查询所有
        List<User> list = userMapper.queryAllUser();
        //获取分页的相关信息
        PageInfo pageInfo = new PageInfo(list);

        return pageInfo;
    }

    public List<User> queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);
    }
}
