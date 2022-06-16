package com.xccaia.service;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.xccaia.dto.UserDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelService {


    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {


        try {
            String fileName = "设备导出";
//            String fileName = "设备导出-" + DateUtils.nowDateTime();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

            int i = 0;
            WriteSheet writeSheet = EasyExcel.writerSheet(i, "设备")
                    .head(UserDTO.class)
                    .registerWriteHandler(new SimpleColumnWidthStyleStrategy(20))//列宽设置成20
                    .build();
            List list = new ArrayList<UserDTO>();
            list.add(new UserDTO(1, "zhangsan"));
            list.add(new UserDTO(2, "lisi"));
            list.add(new UserDTO(3, "wangwu"));

            excelWriter.write(list, writeSheet);

            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
