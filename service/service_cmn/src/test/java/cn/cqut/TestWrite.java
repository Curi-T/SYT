package cn.cqut;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;

/**
 * @author CuriT
 * @Date 2022-7-11 12:23
 */
public class TestWrite {
    public static void main(String[] args) {
        ArrayList<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData data = new UserData();
            data.setUid(i);
            data.setUsername("CuriT"+i);
            list.add(data);
        }
        String fileName = "D:\\AAexcell.xlsx";
        EasyExcel.write(fileName,UserData.class).sheet("用户信息").doWrite(list);
    }
}
