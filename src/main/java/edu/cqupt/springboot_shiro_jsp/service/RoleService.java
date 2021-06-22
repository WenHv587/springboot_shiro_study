package edu.cqupt.springboot_shiro_jsp.service;

import edu.cqupt.springboot_shiro_jsp.entity.Perm;

import java.util.List;

/**
 * @author LWenH
 * @create 2021/6/20 - 18:30
 */
public interface RoleService {

    /**
     * 通过角色获取权限信息
     * @param roleId
     * @return
     */
    List<Perm> getPermsByRoleId(Integer roleId);
}
