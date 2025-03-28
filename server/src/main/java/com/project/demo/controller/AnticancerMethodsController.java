package com.project.demo.controller;

import com.project.demo.entity.AnticancerMethods;
import com.project.demo.service.AnticancerMethodsService;
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
 * 抗癌方法：(AnticancerMethods)表控制层
 *
 */
@RestController
@RequestMapping("/anticancer_methods")
public class AnticancerMethodsController extends BaseController<AnticancerMethods, AnticancerMethodsService> {

    /**
     * 抗癌方法对象
     */
    @Autowired
    public AnticancerMethodsController(AnticancerMethodsService service) {
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
