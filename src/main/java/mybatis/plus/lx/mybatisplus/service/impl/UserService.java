package mybatis.plus.lx.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mybatis.plus.lx.mybatisplus.bean.User;
import mybatis.plus.lx.mybatisplus.dao.UserMapper;
import mybatis.plus.lx.mybatisplus.service.IUserService;
import org.springframework.stereotype.Service;


@Service
public class UserService extends ServiceImpl<UserMapper,User> implements IUserService {

}
