package mybatis.plus.lx.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mybatis.plus.lx.mybatisplus.bean.Area;
import mybatis.plus.lx.mybatisplus.service.impl.AreaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AreaTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void testSelect0(){

    }


    /**
     * 分页获取
     * */
    @Test
    public void testSelect1(){
        IPage<Area> page = areaService.page(new Page<Area>().setCurrent(1).setPages(10), new LambdaQueryWrapper<>(null));
        System.out.println(page);
    }

    /**
     * 获取省市
     * */
    @Test
    public void testSelect2(){
        Area area = new Area();
        area.setAreaName("北京市");
        List<Area> areas = areaService.list(new LambdaQueryWrapper<Area>()
                .like(area.getAreaName() != null, Area::getAreaName, area.getAreaName()));
        areas.forEach(e->{
            System.out.println(e);
        });
    }


    /**
     * 通过父级id获取区域列表  加入缓存
     * */
    @Test
    public void testSelect3(){
        List<Area> areas = areaService.listAreaById(131000000000L);
        areas.forEach(e->{
            System.out.println(e);
        });


    }

}
