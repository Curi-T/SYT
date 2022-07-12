package cn.cqut.yygh.cmn.controller;

import cn.cqut.yygh.cmn.service.DictService;
import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.model.cmn.Dict;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author CuriT
 * @Date 2022-7-11 11:07
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/cmn/dict")
public class DictController {

    private DictService dictService;

    @Autowired
    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    /**
     * 1.根据数据id查询子数据列表
     *
     * @param id 数据id
     * @return 子数据列表
     */
    @ApiOperation(value = "1.根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChlidData(id);
        return Result.ok(list);
    }

    /**
     * 2.导出
     * @param response 无
     */
    @ApiOperation(value="2.导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        dictService.exportData(response);
    }

    /**
     * 3.导入
     * @param file 文件
     * @return
     */
    @ApiOperation(value = "3.导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        dictService.importData(file);
        return Result.ok();
    }



}
