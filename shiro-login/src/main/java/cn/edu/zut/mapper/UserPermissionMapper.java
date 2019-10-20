package cn.edu.zut.mapper;
import cn.edu.zut.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import	java.util.List;

@Mapper
public interface UserPermissionMapper
{
    List<Permission> findByUserName(String username);
}
