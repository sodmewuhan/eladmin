package me.zhengjie.fish.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class BoxInfoDto implements Serializable {

    private Integer id;

    /**  盒子类型 */
    private Integer boxTypoId;

    /** 盒子编号 */
    private String boxNumber;

    /** 注册时间 */
    private Timestamp registeTime;

    /** 创建人 */
    private Timestamp createDate;

    /** 创建时间 */
    private String createBy;

    /** 最后更新人 */
    private Timestamp lastUpdateDate;

    /** 最后更新时间 */
    private String lastUpdateBy;

    /** 塘口ID */
    private Integer fishPondId;
}
