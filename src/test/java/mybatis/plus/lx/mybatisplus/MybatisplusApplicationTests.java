package mybatis.plus.lx.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mybatis.plus.lx.mybatisplus.bean.Area;
import mybatis.plus.lx.mybatisplus.bean.User;
import mybatis.plus.lx.mybatisplus.dao.UserMapper;
import mybatis.plus.lx.mybatisplus.service.impl.AreaService;
import mybatis.plus.lx.mybatisplus.service.impl.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    @Resource
    private UserMapper userMapper;

    @Autowired
    private AreaService areaService;

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }


    @Test
    public void testSelect1() {
        System.out.println(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, 1)));

    }

    @Test
    public void testSelect2() {
        //不用or 默认用and 链接
        Page<User> userPage = new Page<>(1, 3);
        String name = "J";
        Integer age = 18;
        IPage<User> userIPage = userMapper.selectPage(userPage, new LambdaQueryWrapper<User>()
                .like(name != null&&name!="",User::getName,name)
                .like(age != null,User::getAge,age));

        System.out.println(userIPage.getRecords());
    }

    @Test
    public void testSelect3() {
        System.out.println(userService.count(new LambdaQueryWrapper<User>().ge(User::getAge,20)));
    }

    @Test
    public void testSelect4() {
        System.out.println(userService.count(Wrappers.<User>lambdaQuery().gt(User::getAge,20)));
    }

    //乐观锁
    @Test
    public void testSelect5() {
        for (int i = 0; i < 100; i++) {
            User byId = userService.getById(6L);
            boolean b = userService.updateById(new User()
                    .setId(byId.getId())
                    .setAge(18)
                    .setEmail("744606867@qq.com"+i)
                    .setName("lrl"+i)
                    .setVersion(byId.getVersion()));
            System.out.println(b);
        }
    }

    //逻辑删除
    @Test
    public void testSelect6() {
        boolean b = userService.removeById(7L);
        System.out.println(b);
        User byId = userService.getById(7L);
        System.out.println(byId);
    }



    //字段填充测试 insert
    @Test
    public void testSelect7() {
        User user = new User();
        user.setVersion(0).setName("cxzxc").setEmail("qqq").setAge(18).setDeleted(0);
        boolean save = userService.save(user);
        System.out.println(save);
    }

    //字段填充测试   update
    @Test
    public void testSelect8() {
        User user = new User();
        user.setAge(20).setEmail("wqeqeqeqe").setName("czczczc").setVersion(2).setId(1265091742953111559L);
        userService.updateById(user);
    }


    //测试事务
    @Test
    @Transactional
    public void testSelect9(){
        userService.removeById(6L);
        int i = 1/0;
        userService.removeById(12L);
    }

    //增删改查
    @Test
    public void testSelect10(){
        userService.remove(new LambdaQueryWrapper<User>().ge(User::getAge,21));
    }

    //增删改查
    @Test
    public void testSelect11(){
        IPage<User> lrl = userService.page(new Page<>(1, 2), new LambdaQueryWrapper<User>()
                .like(User::getName, "3"));
        System.out.println(lrl.getRecords());
    }

    //增删改查
    @Test
    public void testSelect12(){
        ArrayList<User> objects = new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            User user = new User();
            user.setVersion(0);
            user.setName("xx"+i);
            user.setEmail("dadada"+i);
            user.setAge(i);
            user.setDeleted(0);
            objects.add(user);
        }
        userService.saveBatch(objects,2);

    }

    //增删改查
    @Test
    public void testSelect13(){
        int count = userService.count(new LambdaQueryWrapper<User>().gt(User::getAge, 18));
        System.out.println(count);
    }

    //增删改查
    @Test
    public void testSelect14(){
        Map<String, Object> map = userService.getMap(new LambdaQueryWrapper<User>().ge(User::getAge, 18));
        map.forEach((k,v)->{
            System.out.println(k+"=="+v);
        });
    }
    //md5
    @Test
    public void testSelect16(){
        //用户注册  对密码加密
        Md5Hash md5Hash = new Md5Hash("123","liu",1024);
        System.out.println(md5Hash.toHex());
    }




















}
