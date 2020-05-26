package com.shareblog.service;

import com.shareblog.bean.Role;

import java.util.List;

/**
 * @ClassName RoleService
 * @Author 杨武军
 * @Date 2020/5/19 8:43
 */
public interface RoleService {
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAllRole();

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    Role findRoleById(Integer id);
}
