package edu.cqupt.springboot_shiro_jsp.service;

import edu.cqupt.springboot_shiro_jsp.dao.UserDao;
import edu.cqupt.springboot_shiro_jsp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LWenH
 * @create 2021/6/20 - 0:04
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    void register(User user);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User getUserByName(String username);

    /**
     * 根据用户名查询用户的角色信息
     * 和根据用户名查找用户类似，不过返回的结果不一样
     * 这个只需要返回用户的名称和角色，不需要返回密码和salt
     *
     * @param username
     * @return
     */
    User getUserRolesByName(String username);
}
