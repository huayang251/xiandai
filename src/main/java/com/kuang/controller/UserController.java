package com.kuang.controller;

import com.github.pagehelper.PageInfo;
import com.kuang.pojo.User;
import com.kuang.service.UserService;
import com.kuang.utils.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @auther shkstart
 * @date 2021/5/23 - 16:50
 */
@Controller
public class UserController {


    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    //用户登录
    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public String login(String userCode,String userPassword, Model model, HttpSession session){
        //System.out.println(userCode);
        User user = userService.queryUserByuserCodeandPassword(userCode, userPassword);
        //System.out.println("1111");
        if(user!=null){
            session.setAttribute("USER_SESSION",user);
            if(user.getUserState().equals("1")){
                return "adminmain";
            }else{
                return "usermain";
            }
        }
        model.addAttribute("msg","账号或密码错误，请重新输入！");
        return "login";
    }

    //退出登录
    @RequestMapping("/logout.action")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login.action";
    }

    //向用户登录界面跳转
    @RequestMapping(value = "/login.action",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }


    //向用户注册界面跳转
    @RequestMapping(value = "/toregister" ,method = RequestMethod.POST)
    public String toRegister(){ return "register";}
    //用户注册
    @RequestMapping(value = "/register.action",method = RequestMethod.POST)
    public String register(User user,Model model,HttpSession session){
        System.out.println(user.toString());
        Mail mail = new Mail(user);
        String s = mail.userun();
        session.setAttribute("verification",s);
        session.setAttribute("user",user);
        model.addAttribute("msg","请填写验证码！");
        return "register";

    }

    //验证注册码
    @RequestMapping(value = "/verification",method = RequestMethod.POST)
    public String verification(String registeraction,Model model,HttpSession session){
        System.out.println(registeraction);
        System.out.println(session.getAttribute("verification"));
        if(registeraction.equals(session.getAttribute("verification"))){
            User user = (User) session.getAttribute("user");
            userService.addUser(user);
            model.addAttribute("msg1","注册成功！");
        }else{
            model.addAttribute("msg1","验证码错误！");
        }
        return "register";
    }

    //分页查询全部用户，向用户列表第一页跳转，
    @RequestMapping("/toallUser/{page}")
    public String toallUser(@PathVariable("page") int page,Model model){
        PageInfo<User> pageInfo = userService.getUserByPage(page, 4);
        model.addAttribute("pageInfo", pageInfo);
        return "allUser";
    }

    //分页查询全部用户
    @RequestMapping("/toallUser")
    public String showUserByPage(int page,Model model){

        PageInfo<User> pageInfo = userService.getUserByPage(page, 4);
        model.addAttribute("pageInfo", pageInfo);
        return "allUser";
    }


    //按照用户姓名模糊查询
    @RequestMapping("/queryUser")
    public String queryUser(String queryUserName,Model model){
        List<User> list = userService.queryUserByName(queryUserName);
        model.addAttribute("list",list);
        return "allUser";
    }

    //跳转到增加用户页面
    @RequestMapping("/toAddUser")
    public String toAddUser(){return "addUser";}


    //添加用户的请求
    @RequestMapping("/addUser")
    public String addUser(User user){
        System.out.println("addUser=>"+user);
        userService.addUser(user);
        return "redirect:/toallUser";
    }

    //跳转到修改用户页面
    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(int id,Model model){
        User user = userService.queryUserById(id);
        model.addAttribute(user);
        return "updateUser";
    }

    //修改用户的请求
    @RequestMapping("/updateUser")
    public String updateUser(User user){
        System.out.println("update=>"+user);
        userService.updateUser(user);
        return "redirect:/toallUser";
    }

    //删除用户
    @RequestMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId")int id){

        userService.deleteUserById(id);
        return "redirect:/toallUser";
    }
}
