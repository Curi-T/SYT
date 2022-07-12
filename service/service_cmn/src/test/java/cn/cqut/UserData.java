package cn.cqut;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author CuriT
 * @Date 2022-7-11 12:22
 */
@Data
public class UserData {
    @ExcelProperty(value = "编号",index = 1)
    private int uid;

    @ExcelProperty(value = "名称",index = 2)
    private String username;
}
