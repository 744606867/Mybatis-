package mybatis.plus.lx.mybatisplus.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //插入操作
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //this.setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("createTime", dtf.format(LocalDateTime.now()),metaObject);
        this.setFieldValByName("updateTime", dtf.format(LocalDateTime.now()),metaObject);
    }

    //更新操作
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime", dtf.format(LocalDateTime.now()),metaObject);
    }
}
