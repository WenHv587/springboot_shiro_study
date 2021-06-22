package edu.cqupt.springboot_shiro_jsp.dao;

import edu.cqupt.springboot_shiro_jsp.entity.Perm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LWenH
 * @create 2021/6/20 - 18:23
 */
@Mapper
@Repository
public interface RoleDao {

    /**
     * 通过角色获取权限信息
     * @param roleId
     * @return
     */
    List<Perm> getPermsByRoleId(Integer roleId);
}
