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
     * @param id
     * @return
     */
    List<Dict> findChlidData(Long id);

    /**
     * 2.导出
     * @param response
     */
    void exportData(HttpServletResponse response);

    /**
     * 3.导入
     * @param file
     */
    void importData(MultipartFile file);
}
