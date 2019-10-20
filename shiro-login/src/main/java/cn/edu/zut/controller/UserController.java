package cn.edu.zut.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController
{
    @RequiresPermissions("user:user")//表示需要某人拥有权限才可以
    @RequestMapping("list")
    public String userList(Model model)
    {
        model.addAttribute("value","获取用户信息");
        return "user";
    }

    @RequiresPermissions("user:add")//表示需要某人拥有权限才可以
    @RequestMapping("add")
    public String userAdd(Model model){
        model.addAttribute("value","新增用户");
        return "user";
    }

    @RequiresPermissions("user:delete")//表示需要某人拥有权限才可以
    @RequestMapping("delete")
    public String userDelete(Model model){
        model.addAttribute("value","删除用户");
        return "user";
    }


}
