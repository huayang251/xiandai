package com.kuang.dao;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther shkstart
 * @date 2021/5/22 - 23:58
 */
public interface UserMapper {

    //根据账号密码查询用户
    User queryUserByuserCodeandPassword(@Param("userCode") String userCode,@Param("userPassword") String password);

    //增加一个用户
    int addUser(User user);

    //删除一个用户
    int deleteUserById(@Param("userId") int id);

    //更新一个用户
    int updateUser(User user);

    //根据Id查询一个用户
    User queryUserById(@Param("userId") int id);

    //查询全部用户
    List<User> queryAllUser();

    //根据名字模糊查询
    List<User> queryUserByName(@Param("userName") String userName);

}
