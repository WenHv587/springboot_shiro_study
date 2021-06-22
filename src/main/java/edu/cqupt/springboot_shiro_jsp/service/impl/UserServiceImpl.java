package edu.cqupt.springboot_shiro_jsp.service.impl;

import edu.cqupt.springboot_shiro_jsp.dao.UserDao;
import edu.cqupt.springboot_shiro_jsp.entity.User;
import edu.cqupt.springboot_shiro_jsp.service.UserService;
import edu.cqupt.springboot_shiro_jsp.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LWenH
 * @create 2021/6/20 - 0:05
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 在业务层中对用户的密码进行加密 使用md5+salt+hash
        String salt = SaltUtils.getSalt(5);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public User getUserRolesByName(String username) {
        return userDao.getUserRolesByName(username);
    }
}
