package com.xccaia.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @ExcelProperty(value = {"id"}, index = 0)
    private int id;
    @ExcelProperty(value = {"name"}, index = 1)
    private String name;
}
