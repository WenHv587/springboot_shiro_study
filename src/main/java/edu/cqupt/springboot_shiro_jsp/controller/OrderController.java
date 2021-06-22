package edu.cqupt.springboot_shiro_jsp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LWenH
 * @create 2021/6/20 - 14:06
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/saveOrder")
    @RequiresRoles("admin")
    @RequiresPermissions("order:save:*")
    public String saveOrder() {
        System.out.println("进入订单保存方法");
        // 使用编码的方式授权
        Subject subject = SecurityUtils.getSubject();
        // 可以基于角色身份做限制，也可以通过权限字符串对资源做限制
        if (subject.hasRole("admin") && subject.isPermitted("order:save:*")) {
            return "成功保存订单！";
        } else {
            return "无访问权限！";
        }
    }
}
