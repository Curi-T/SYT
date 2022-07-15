package cn.cqut.yygh.hosp.service;

import cn.cqut.yygh.model.hosp.Department;
import cn.cqut.yygh.vo.hosp.DepartmentQueryVo;
import cn.cqut.yygh.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author CuriT
 */
public interface DepartmentService {
    /**
     * 上传科室
     *
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 条件分页查询
     *
     * @param page
     * @param limit
     * @param departmentQueryVo
     * @return
     */
    Page<Department> selectPage(int page, int limit, DepartmentQueryVo departmentQueryVo);

    /**
     * 删除科室
     *
     * @param hoscode
     * @param depcode
     */
    void remove(String hoscode, String depcode);

    /**
     * 根据医院编号，查询医院所有科室列表
     *
     * @param hoscode
     * @return
     */
    List<DepartmentVo> findDeptTree(String hoscode);

    /**
     * 根据课设编号，和医院编号，查询科室名称
     * @param hoscode
     * @param depcode
     * @return
     */
    String getDepName(String hoscode, String depcode);
}
