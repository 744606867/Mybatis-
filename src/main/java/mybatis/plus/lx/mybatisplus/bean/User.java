package mybatis.plus.lx.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName(value = "user")
@Accessors(chain = true)
public class User implements Serializable {
    @TableId(type = IdType.AUTO)

    private Long id;
    private String name;
    private Integer age;
    private String email;
    //乐观锁
    @Version
    private Integer version;
    //逻辑删除
    //@TableLogic
    private Integer deleted;

    // 注意！这里需要标记为填充字段
    @TableField(value= "createTime",fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    // 注意！这里需要标记为填充字段
    @TableField(value= "updateTime",fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

}