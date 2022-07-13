package cn.cqut.yygh.hosp.service.impl;

import cn.cqut.yygh.hosp.repository.DepartmentRepository;
import cn.cqut.yygh.hosp.service.DepartmentService;
import cn.cqut.yygh.model.hosp.Department;
import cn.cqut.yygh.vo.hosp.DepartmentQueryVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author CuriT
 * @Date 2022-7-12 16:30
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 3.上传科室
     *
     * @param paramMap
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        //paramMap转换成department对象
        Department department = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Department.class);
        Department departmentExist = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(), department.getDepcode());
        if (null != departmentExist) {
            //copy不为null的值，该方法为自定义方法
            BeanUtils.copyProperties(department, departmentExist, Department.class);
            departmentRepository.save(departmentExist);
        } else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }

    }

    @Override
    public Page<Department> selectPage(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        //0为第一页
        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo, department);
        department.setIsDeleted(0);

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

        //创建实例
        Example<Department> example = Example.of(department, matcher);
        Page<Department> pages = departmentRepository.findAll(example, pageable);
        return pages;


    }

    /**
     * 5.删除科室
     *
     * @param hoscode
     * @param depcode
     */
    @Override
    public void remove(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (null != department) {
            departmentRepository.deleteById(department.getId());
        }
    }
}
