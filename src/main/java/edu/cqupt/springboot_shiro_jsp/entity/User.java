package edu.cqupt.springboot_shiro_jsp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author LWenH
 * @create 2021/6/20 - 0:02
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 8882978295358058409L;

    private Integer id;
    private String username;
    private String password;
    private String salt;
    /**
     * 用户的角色集合
     * 用户和角色绑定
     */
    private List<Role> roleList;
}
