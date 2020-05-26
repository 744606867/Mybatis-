package mybatis.plus.lx.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mybatis.plus.lx.mybatisplus.bean.Area;

import java.util.List;

public interface IArea extends IService<Area> {

    List<Area> listAreaById(Long pid);

    void removeAreaCacheByParentId(Long pid);
}
