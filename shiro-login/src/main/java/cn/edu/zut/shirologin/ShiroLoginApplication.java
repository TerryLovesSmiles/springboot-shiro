package cn.edu.zut.shirologin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@MapperScan("cn.edu.zut.mapper")
@ComponentScan("cn.edu.zut")
@SpringBootApplication
public class ShiroLoginApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ShiroLoginApplication.class, args);
    }

}
