package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 医生用户：(DoctorUser)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DoctorUser")
public class DoctorUser implements Serializable {

    // DoctorUser编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_user_id")
    private Integer doctor_user_id;

    // 医生姓名
    @Basic
    private String doctors_name;
    // 医生性别
    @Basic
    private String doctors_gender;
    // 医生职称
    @Basic
    private String professional_title_of_doctor;












    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;


    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
