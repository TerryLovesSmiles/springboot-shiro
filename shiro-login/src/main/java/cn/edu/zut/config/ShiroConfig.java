package cn.edu.zut.config;

import cn.edu.zut.shiro.ShiroRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
/*
* 记得在controller层加 @EnableConfiguration
* */

@Configuration
public class ShiroConfig
{
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        //设置securityManager管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后跳转的url
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        LinkedHashMap<String,String> filterChainDefinitionMap=new LinkedHashMap<String, String> ();

        // 定义filterChain，静态资源不拦截
        filterChainDefinitionMap.put("/css/*","anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        // 配置退出过滤器，其中具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/","anon");//根路径都不拦截
        // 除了上面以外所有url都必须认证通过才可以访问，未通过认证自动访问LoginUrl
        filterChainDefinitionMap.put("/**","user");//根路径下的子目录下都拦截//加了rememberMe功能，就从authc变成了user

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        // 配置SecurityManager，并注入shiroRealm
        DefaultWebSecurityManager securityManager  = new DefaultWebSecurityManager();
        securityManager .setRealm(shiroRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        return  securityManager;


    }

    @Bean
    public ShiroRealm shiroRealm(){
        // 配置Realm，需自己实现
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;

    }

    /*
    * 用cookie 对象来实现rememberMe功能
    * */

    public SimpleCookie rememberMeCookie(){
        // 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie=new SimpleCookie("rememberMe");
        // 设置cookie的过期时间，单位为秒，这里为一天
        cookie.setMaxAge(86400);
        return cookie;
    }


        public CookieRememberMeManager rememberMeManager()

    {
        CookieRememberMeManager cookieMe = new CookieRememberMeManager();
        cookieMe.setCookie(rememberMeCookie());
        // // rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieMe.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieMe;

}

/*开启权限相关的注解  下面三个都是*/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator; }
}
