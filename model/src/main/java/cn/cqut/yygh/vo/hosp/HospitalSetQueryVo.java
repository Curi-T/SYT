package cn.cqut.yygh.vo.hosp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author CuriT
 */
@Data
public class HospitalSetQueryVo {

    @ApiModelProperty(value = "医院名称")
    private String hosname;

    @ApiModelProperty(value = "医院编号")
    private String hoscode;
}
