package cn.cqut.yygh.msm.service;

/**
 * @author CuriT
 * @Date 2022-7-17 10:35
 */
public interface MsmService {
    /**
     * 1.发送手机验证码
     *
     * @param phone
     * @param code
     * @return
     */
    boolean send(String phone, String code);
}
