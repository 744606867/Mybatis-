package mybatis.plus.lx.mybatisplus.shiro.realms;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import mybatis.plus.lx.mybatisplus.bean.User;
import mybatis.plus.lx.mybatisplus.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 自定义relam  shiro 交互数据
* */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private IUserService iUserService;
    //授权
    //principalCollection  身份集合 （用户名）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //登录的用户名 （走到这里说明 已经认证成功）
        String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String principal = (String)authenticationToken.getPrincipal();
        System.out.println(principal);
        //通过用户名查询用户信息（密码）
        User user = iUserService.getOne(new LambdaQueryWrapper<User>().eq(User::getName, principal), false);
        System.out.println(user);
        if (user != null){
            if (user.getName().equals(principal)){                             //对前端传过来的密码加盐  这里的盐是用户名
                return new SimpleAuthenticationInfo(principal,user.getEmail(), ByteSource.Util.bytes(user.getName()),this.getName());
            }else {
                return new SimpleAuthenticationInfo(principal,"",getName());
            }
        }
        return null;
    }

    //用户注册
    public void testSelect16(){
        //用户注册  对密码加密
        Md5Hash md5Hash = new Md5Hash("123","liu",1024);
        System.out.println(md5Hash.toHex());
    }
}
