package cn.edu.zut.controller;

import cn.edu.zut.pojo.ResponseBo;
import cn.edu.zut.pojo.User;
import cn.edu.zut.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@EnableAutoConfiguration
@Controller
public class LoginController
{
    @GetMapping("/login")
    public String login(){

        return "login";
    }

    /*
    UnknownAccountException等异常为Shiro自带异常，Shiro具有丰富的运行时AuthenticationException层次结构，可以准确指出尝试失败的原因。你可以包装在一个try/catch块，并捕捉任何你希望的异常，并作出相应的反应
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(String username,String password,Boolean rememberMe){
      String password1 = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token=new UsernamePasswordToken(username,password1,rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            return ResponseBo.ok();
        }catch (UnknownAccountException e){
            return ResponseBo.error(e.getMessage());
        }catch (IncorrectCredentialsException e){
            return ResponseBo.error(e.getMessage());
        }catch (LockedAccountException e){
            return ResponseBo.error(e.getMessage());

        }catch (AuthenticationException e){
            return ResponseBo.error("认证失败");
        }

    }

    @RequestMapping("/")
    public String redirectIndex(){
        return "redirect: /index";
    }


    //登录成功后，根据之前在ShiroConfig中的配置shiroFilterFactoryBean.setSuccessUrl("/index")，页面会自动访问/index路径。
    @RequestMapping("/index")
    public String index(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        return "index";

    }

    @GetMapping("/403")
    public String forbid() {
        return "403";
    }


}
