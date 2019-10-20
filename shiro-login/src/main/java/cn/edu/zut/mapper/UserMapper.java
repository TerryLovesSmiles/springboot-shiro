package cn.edu.zut.mapper;

import cn.edu.zut.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper

{
    User findByUserName(String userName);

}
