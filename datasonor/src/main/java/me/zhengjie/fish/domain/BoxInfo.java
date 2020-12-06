package me.zhengjie.fish.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="box_info_t")
public class BoxInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Integer id;

    @Column(name = "box_typo_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = " 盒子类型")
    private Integer boxTypoId;

    @Column(name = "box_number",unique = true,nullable = false)
    @NotBlank
    @ApiModelProperty(value = "盒子编号")
    private String boxNumber;

    @Column(name = "registe_time")
    @ApiModelProperty(value = "注册时间")
    private Timestamp registeTime;

    @Column(name = "create_date")
    @ApiModelProperty(value = "创建人")
    private Timestamp createDate;

    @Column(name = "create_by")
    @ApiModelProperty(value = "创建时间")
    private String createBy;

    @Column(name = "last_update_date")
    @ApiModelProperty(value = "最后更新人")
    private Timestamp lastUpdateDate;

    @Column(name = "last_update_by")
    @ApiModelProperty(value = "最后更新时间")
    private String lastUpdateBy;

    @Column(name = "fish_pond_id")
    @ApiModelProperty(value = "塘口ID")
    private Integer fishPondId;

    public void copy(BoxInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
