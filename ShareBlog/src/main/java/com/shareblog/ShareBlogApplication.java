package com.shareblog;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @ClassName ShareBlogApplication
 * @Author 杨武军
 * @Date 2020/5/20 10:13
 */
@SpringBootApplication
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)// 解决jmx重复注册bean的问题
@Import(FdfsClientConfig.class) // 导入FastDFS-Client组件
public class ShareBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShareBlogApplication.class,args);
    }
}
