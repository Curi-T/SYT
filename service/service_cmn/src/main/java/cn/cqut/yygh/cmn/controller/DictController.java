package cn.cqut.yygh.cmn.controller;

import cn.cqut.yygh.cmn.service.DictService;
import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.model.cmn.Dict;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author CuriT
 * @Date 2022-7-11 11:07
 */
@ApiOperation(value = "数据字典")
@RestController
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
     *
     * @param response 无
     */
    @ApiOperation(value = "2.导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        dictService.exportData(response);
    }

    /**
     * 3.导入
     *
     * @param file 文件
     * @return
     */
    @ApiOperation(value = "3.导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        dictService.importData(file);
        return Result.ok();
    }

    /**
     * 4.获取数据字典名称
     *
     * @param parentDictCode
     * @param value
     * @return
     */
    @ApiOperation(value = "4.获取数据字典名称")
    @GetMapping(value = "/getName/{parentDictCode}/{value}")
    public String getName(
            @ApiParam(name = "parentDictCode", value = "上级编码", required = true)
            @PathVariable("parentDictCode") String parentDictCode,

            @ApiParam(name = "value", value = "值", required = true)
            @PathVariable("value") String value) {
        return dictService.getNameByParentDictCodeAndValue(parentDictCode, value);
    }

    /**
     * 5.获取数据字典名称
     *
     * @param value
     * @return
     */
    @ApiOperation(value = "5.获取数据字典名称")
    @ApiImplicitParam(name = "value", value = "值", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/getName/{value}")
    public String getName(
            @ApiParam(name = "value", value = "值", required = true)
            @PathVariable("value") String value) {
        return dictService.getNameByParentDictCodeAndValue("", value);
    }

    /**
     * 6.根据dictCode获取下一级节点
     *
     * @param dictCode
     * @return
     */
    @ApiOperation(value = "6.根据dictCode获取下一级节点")
    @GetMapping(value = "/findByDictCode/{dictCode}")
    public Result<List<Dict>> findByDictCode(
            @ApiParam(name = "dictCode", value = "节点编码", required = true)
            @PathVariable String dictCode) {
        List<Dict> list = dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }


}
