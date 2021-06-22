package edu.cqupt.springboot_shiro_jsp.controller;

import edu.cqupt.springboot_shiro_jsp.entity.User;
import edu.cqupt.springboot_shiro_jsp.service.UserService;
import edu.cqupt.springboot_shiro_jsp.utils.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author LWenH
 * @create 2021/6/19 - 21:31
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 生成验证码图片
     *
     * @param session
     * @param response
     */
    @RequestMapping("/image")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        // 生成验证码
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 将验证码放入session
        session.setAttribute("verifyCode", verifyCode);
        // 将验证码存入图片
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(100, 30, outputStream, verifyCode);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, String verifyCode, HttpSession session) {
        // 首先比较验证码
        String code = (String) session.getAttribute("verifyCode");
        try {
            if (code.equalsIgnoreCase(verifyCode)) {
                // Spring项目中securityManager会自动注入到SecurityUtils中
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(username, password));
                System.out.println("登录成功");
                return "redirect:/index.jsp";
            } else {
                throw new RuntimeException("验证码错误!");
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名不存在！");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("验证码错误！");
        }
        return "redirect:/login.jsp";
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }
}
