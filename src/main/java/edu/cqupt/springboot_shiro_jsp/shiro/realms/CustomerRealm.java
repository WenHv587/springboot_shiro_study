package edu.cqupt.springboot_shiro_jsp.shiro.realms;

import edu.cqupt.springboot_shiro_jsp.entity.Perm;
import edu.cqupt.springboot_shiro_jsp.entity.Role;
import edu.cqupt.springboot_shiro_jsp.entity.User;
import edu.cqupt.springboot_shiro_jsp.service.RoleService;
import edu.cqupt.springboot_shiro_jsp.service.UserService;
import edu.cqupt.springboot_shiro_jsp.shiro.salt.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author LWenH
 * @create 2021/6/19 - 20:11
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("权限验证：" + primaryPrincipal);
        // 根据主身份信息（用户名）获取到角色和权限信息
        User user = userService.getUserRolesByName(primaryPrincipal);
        System.out.println(user);
        List<Role> roleList = user.getRoleList();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (!CollectionUtils.isEmpty(roleList)) {
            // 每个user可能对应着多种角色，每个角色又有可能对应着多个权限
            roleList.forEach(role -> {
                authorizationInfo.addRole(role.getRoleName());
                List<Perm> permList = roleService.getPermsByRoleId(role.getRoleId());
                System.out.println(permList);
                permList.forEach(perm -> authorizationInfo.addStringPermission(perm.getName()));
            });
            return authorizationInfo;
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("========认证身份========");
        String principal = (String) authenticationToken.getPrincipal();
        System.out.println("用户名：" + principal);

        /*
            在数据库中以用户名查找用户 需要用到service组件
            如果CustomerRealm不是容器中的组件，就不能自动注入
            可以通过ApplicationContextAware拿到Spring底层的上下文组件，来获取service组件

            这里CustomerRealm其实是交给容器中管理了的，ShiroConfig类中使用了@Bean注册bean
         */
        User user = userService.getUserByName(principal);
        if (! ObjectUtils.isEmpty(user)) {
            // 返回认证信息
            return new SimpleAuthenticationInfo(user.getUsername(),
                    user.getPassword(), new MyByteSource(user.getSalt()), this.getName());
        }
        return null;
    }
}
