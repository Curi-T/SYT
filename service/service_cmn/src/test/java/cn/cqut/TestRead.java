package cn.cqut;

import com.alibaba.excel.EasyExcel;

/**
 * @author CuriT
 * @Date 2022-7-11 12:38
 */
public class TestRead {
    public static void main(String[] args) {
        String fileName = "D:\\AAexcell.xlsx";
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
