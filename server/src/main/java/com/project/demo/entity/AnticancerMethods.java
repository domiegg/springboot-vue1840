package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 抗癌方法：(AnticancerMethods)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "AnticancerMethods")
public class AnticancerMethods implements Serializable {

    // AnticancerMethods编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anticancer_methods_id")
    private Integer anticancer_methods_id;

    // 抗癌标题
    @Basic
    private String anti_cancer_title;
    // 抗癌类型
    @Basic
    private String anticancer_type;
    // 封面图片
    @Basic
    private String cover_photo;
    // 发布时间
    @Basic
    private Timestamp release_time;
    // 内容详情
    @Basic
    private String content_details;

    // 点击数
    @Basic
    private Integer hits;

    // 点赞数
    @Basic
    private Integer praise_len;












    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
