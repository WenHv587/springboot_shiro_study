package edu.cqupt.springboot_shiro_jsp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author LWenH
 * @create 2021/6/20 - 15:16
 *
 * 用户的角色信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 8026037546989335085L;
    private Integer roleId;
    private String roleName;
    /**
     * 权限信息的集合
     * 角色和权限绑定
     */
    private List<Perm> permList;
}
