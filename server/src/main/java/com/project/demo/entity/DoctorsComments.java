package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 医生点评：(DoctorsComments)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DoctorsComments")
public class DoctorsComments implements Serializable {

    // DoctorsComments编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctors_comments_id")
    private Integer doctors_comments_id;

    // 发布患者
    @Basic
    private Integer post_patient;
    // 患者姓名
    @Basic
    private String patient_name;
    // 发布时间
    @Basic
    private Timestamp release_time;
    // 发布内容
    @Basic
    private String publish_content;
    // 点评回复
    @Basic
    private String comment_response;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
