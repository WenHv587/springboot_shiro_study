package edu.cqupt.springboot_shiro_jsp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author LWenH
 * @create 2021/6/20 - 15:18
 *
 * 权限
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Perm implements Serializable {
    private static final long serialVersionUID = 7853941678361812021L;
    private Integer id;
    private String name;
    private String url;
}
