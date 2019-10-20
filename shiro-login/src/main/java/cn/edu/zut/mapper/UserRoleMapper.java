package cn.edu.zut.mapper;
import cn.edu.zut.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper
{
    List <Role> findUserByUsername(String UserName);

}
