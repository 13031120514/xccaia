package com.xccaia.controller;

import com.xccaia.service.ExcelService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "UserController : 用户信息处理")
public class ExcelController {


    @Autowired
    private ExcelService excelService;

    @PostMapping("/integration/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        excelService.exportExcel(request, response);
    }


}
