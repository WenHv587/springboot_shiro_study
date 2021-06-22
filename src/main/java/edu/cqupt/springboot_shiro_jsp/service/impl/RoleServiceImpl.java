package edu.cqupt.springboot_shiro_jsp.service.impl;

import edu.cqupt.springboot_shiro_jsp.dao.RoleDao;
import edu.cqupt.springboot_shiro_jsp.entity.Perm;
import edu.cqupt.springboot_shiro_jsp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LWenH
 * @create 2021/6/20 - 18:31
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Perm> getPermsByRoleId(Integer roleId) {
        return roleDao.getPermsByRoleId(roleId);
    }
}
