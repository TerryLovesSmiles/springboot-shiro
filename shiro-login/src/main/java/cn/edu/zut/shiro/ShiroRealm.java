package cn.edu.zut.shiro;

import cn.edu.zut.mapper.UserMapper;
import cn.edu.zut.mapper.UserPermissionMapper;
import cn.edu.zut.mapper.UserRoleMapper;
import cn.edu.zut.pojo.Permission;
import cn.edu.zut.pojo.Role;
import cn.edu.zut.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm
{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserPermissionMapper userPermissionMapper;
    //授权  Authorization  获取用户角色和权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal)
    {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();
        System.out.println("用户" + username + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //获取用户角色集
        List<Role> roleList = userRoleMapper.findUserByUsername(username);
        Set<String>  roleSet=new HashSet<>();
        for (Role role :roleList
             )
        {
            roleSet.add(role.getName());
        }
        System.out.println(roleSet);
        simpleAuthorizationInfo.setRoles(roleSet);

        //获取用户权限集
        List<Permission> permissionList = userPermissionMapper.findByUserName(username);
        Set<String> permissionSet = new HashSet<String>();
        for (Permission p : permissionList) {
            permissionSet.add(p.getName());
        }
        System.out.println(permissionSet);
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;



    }

    //Authentication  登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        // 从token里获取用户输入的用户名和密码
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        System.out.println("用户"+username+"认证-----ShiroRealm.doGetAuthenticationInfo");

        // 通过用户名到数据库查询用户信息
        /*
        * 其中UnknownAccountException等异常为Shiro自带异常，Shiro具有丰富的运行时AuthenticationException层次结构，可以准确指出尝试失败的原因。你可以包装在一个try/catch块，并捕捉任何你希望的异常，并作出相应的反应
        * */
        User user = userMapper.findByUserName(username);
        if(user == null){
            throw new UnknownAccountException("用户名或密码错误");
        }
        if (!password.equals(user.getPassword())){
            throw new IncorrectCredentialsException("密码错误");
        }
        if (user.getStatus().equals("0")){
            throw  new LockedAccountException("账户已被锁定，请联系管理员");
        }
        /*
        * 虽然我们可以准确的获取异常信息，并根据这些信息给用户提示具体错误，但最安全的做法是在登录失败时仅向用户显示通用错误提示信息，例如“用户名或密码错误”。这样可以防止数据库被恶意扫描。
        * */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());

        return info;
    }
}
