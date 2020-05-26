package com.shareblog.service.impl;

import com.shareblog.bean.Role;
import com.shareblog.mapper.RoleMapper;
import com.shareblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Author 杨武军
 * @Date 2020/5/19 8:48
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole() {
        return roleMapper.selectList(null);
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleMapper.selectById(id);
    }
}
