package mybatis.plus.lx.mybatisplus.Controller;

import mybatis.plus.lx.mybatisplus.bean.Area;
import mybatis.plus.lx.mybatisplus.service.impl.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
public class AreaAction {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/")
    public void testSelect3(@Valid Area area){
        List<Area> areas = areaService.listAreaById(area.getParentId());
        areas.forEach(e->{
            System.out.println(e);
        });


    }
}
