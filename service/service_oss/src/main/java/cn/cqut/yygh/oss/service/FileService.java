package cn.cqut.yygh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuriT
 * @Date 2022-7-18 09:17
 */
public interface FileService {
    /**
     * 上传文件到阿里云oss
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
