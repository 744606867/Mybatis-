package mybatis.plus.lx.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mybatis.plus.lx.mybatisplus.bean.Area;
import mybatis.plus.lx.mybatisplus.dao.AreaMapper;
import mybatis.plus.lx.mybatisplus.service.IArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaService extends ServiceImpl<AreaMapper,Area> implements IArea  {

    @Autowired
    @Resource
    private AreaMapper areaMapper;

    @Override
    @Cacheable(cacheNames = "area", key = "#pid")
    public List<Area> listAreaById(Long pid) {
        return  areaMapper.selectList(new LambdaQueryWrapper<Area>().eq(Area::getParentId,pid));
    }

    @Override
    @CacheEvict(cacheNames = "area", key = "#pid")
    public void removeAreaCacheByParentId(Long pid) {

    }
}
