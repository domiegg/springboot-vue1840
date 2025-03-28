package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 抗癌分类：(AnticancerClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "AnticancerClassification")
public class AnticancerClassification implements Serializable {

    // AnticancerClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anticancer_classification_id")
    private Integer anticancer_classification_id;

    // 抗癌类型
    @Basic
    private String anticancer_type;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
