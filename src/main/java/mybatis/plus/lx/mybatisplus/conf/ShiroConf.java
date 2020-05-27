package mybatis.plus.lx.mybatisplus.conf;

import mybatis.plus.lx.mybatisplus.shiro.realms.AuthRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
/**
 * shiro管理配置
 * */
@Configuration
public class ShiroConf {

    //shiro 拦截所有请求
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置公共资源
        //配置受限资源
        HashMap<String, String> map = new HashMap<>();
        //直接访问页面方式
       /* map.put("/index1.html","authc");//authc 需要认证授权
        map.put("/page/attributePage.html","authc");*/

        //通过springmvc 访问方式
        //map.put("/attributePage","authc"); //anon 匿名访问  不需要认证授权
        map.put("/gologin","anon"); //anon 匿名访问  不需要认证授权
        //map.put("/toindex","anon"); //anon 匿名访问  不需要认证授权
        map.put("/**","authc"); //anon authc 需要认证授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //没有认证授权 跳转到login页面
        shiroFilterFactoryBean.setLoginUrl("/tologinPage");
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }


    @Bean
    public Realm realm(){
        AuthRealm authRealm = new AuthRealm();
        //设置MD5加密    对前端传来的密码进行加密
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //对密码 进行hash 散列
        hashedCredentialsMatcher.setHashIterations(1024);
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher);
       return authRealm;
    }


}
