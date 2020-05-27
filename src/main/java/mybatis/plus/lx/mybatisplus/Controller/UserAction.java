package mybatis.plus.lx.mybatisplus.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserAction {

    @RequestMapping("/toindex")
    public String toindex() {
        System.out.println("toindex");
        return "index1";
    }

    @RequestMapping("/attributePage")
    public String toattributepage() {
        System.out.println("toattributepage");
        return "page/attributePage";
    }


    @RequestMapping("/tologinPage")
    public String tologin() {
        System.out.println("tologin");
        return "login";
    }

    @RequestMapping(value = "/gologin",method = RequestMethod.POST)
    public String login(String name, String email) {
        //获取主体对象
        System.out.println(name + "  " + email);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(name, email));
           return "redirect:toindex";
        } catch (UnknownAccountException e) {
            System.out.println("账号错误");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }
        return "redirect:tologinPage";
    }


    @RequestMapping("/logout")
    public void logout() {
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}