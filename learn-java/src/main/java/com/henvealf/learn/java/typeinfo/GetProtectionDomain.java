package com.henvealf.learn.java.typeinfo;

import java.security.Permission;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.util.Enumeration;

/**
 * Class.getProtectionDomain
 *
 * 获取 Class 所在文件的一些信息。
 * 一层一层往下剥离就可。
 * @author hongliang.yin/Henvealf
 * @date 2020-01-12
 */
public class GetProtectionDomain {

    public static void main(String[] args) {
        Class clazz = GetProtectionDomain.class;
        ProtectionDomain protectionDomain = clazz.getProtectionDomain();
        System.out.println(protectionDomain);

        PermissionCollection permissions = protectionDomain.getPermissions();
        System.out.println(permissions);

        Enumeration<Permission> elements = permissions.elements();
        while (elements.hasMoreElements()) {
            Permission permission = elements.nextElement();
            // 获取 class 所在路径。
            System.out.println("name: " + permission.getName());
            System.out.println("actions: " + permission.getActions());
            System.out.println();
        }

        // 获取 class 所在路径。
        String path = protectionDomain.getCodeSource().getLocation().getPath();
        System.out.println("path: " + path);
    }
}
