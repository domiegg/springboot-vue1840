package com.project.demo.controller;

import com.project.demo.entity.DoctorUser;
import com.project.demo.service.DoctorUserService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 医生用户：(DoctorUser)表控制层
 *
 */
@RestController
@RequestMapping("/doctor_user")
public class DoctorUserController extends BaseController<DoctorUser, DoctorUserService> {

    /**
     * 医生用户对象
     */
    @Autowired
    public DoctorUserController(DoctorUserService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
