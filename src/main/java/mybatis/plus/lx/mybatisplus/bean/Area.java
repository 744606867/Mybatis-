package mybatis.plus.lx.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@TableName("tz_area")
public class Area implements Serializable {
    private static final long serialVersionUID = -6013320537436191451L;
    @TableId

    private Long areaId;

    private String areaName;

    private Long parentId;

    private Integer level;

    @TableField(exist = false)
    private List<Area> areas;
}

