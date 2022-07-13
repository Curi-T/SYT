package cn.cqut.yygh.hosp.service;

import cn.cqut.yygh.model.hosp.Department;
import cn.cqut.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;


import java.util.Map;

/**
 * @author CuriT
 */
public interface DepartmentService {
    /**
     * 上传科室
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 条件分页查询
     * @param page
     * @param limit
     * @param departmentQueryVo
     * @return
     */
    Page<Department> selectPage(int page, int limit, DepartmentQueryVo departmentQueryVo);

    /**
     * 删除科室
     * @param hoscode
     * @param depcode
     */
    void remove(String hoscode, String depcode);
}
