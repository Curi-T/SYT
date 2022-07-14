package cn.cqut.yygh.cmn.service;

import cn.cqut.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author CuriT
 */
public interface DictService extends IService<Dict> {
    /**
     * 1.根据数据id查询子数据列表
     *
     * @param id
     * @return
     */
    List<Dict> findChlidData(Long id);

    /**
     * 2.导出
     *
     * @param response
     */
    void exportData(HttpServletResponse response);

    /**
     * 3.导入
     *
     * @param file
     */
    void importData(MultipartFile file);

    /**
     * 4.获取数据字典名称
     *
     * @param parentDictCode
     * @param value
     * @return
     */
    String getNameByParentDictCodeAndValue(String parentDictCode, String value);

    /**
     * 6.根据dictCode获取下一级节点
     * @param dictCode
     * @return
     */
    List<Dict> findByDictCode(String dictCode);
}
