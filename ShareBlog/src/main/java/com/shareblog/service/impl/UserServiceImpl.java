package com.shareblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareblog.exception.BaseException;
import com.shareblog.response.Status;
import com.shareblog.utils.IdWorker;
import com.shareblog.bean.User;
import com.shareblog.mapper.UserMapper;
import com.shareblog.service.RoleService;
import com.shareblog.service.UserService;
import com.shareblog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Author 杨武军
 * @Date 2020/5/18 13:32
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RoleService roleService;

    @Override
    public void saveUser(User user) {
        if (findUserByUsername(user.getUsername())!=null){
            throw new BaseException(Status.FAILED,"用户名已存在！");
        }
        user.setId(idWorker.nextId());
        user.setEnabled(true);
        user.setRegtime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        User own = findUserById(user.getId());//数据库中的用户信息
        //用户名需要更新
        if (!own.getUsername().equals(user.getUsername())){
            //更新的用户名已存在
            if (findUserByUsername(user.getUsername())!=null){
                throw new BaseException(Status.FAILED,"用户名已存在！");
            }
        }
        userMapper.updateById(user);
    }

    @Override
    public void changeStatus(Long id, Boolean status) {
        User user = findUserById(id);
        if (user==null){
            throw new BaseException(Status.FAILED,"用户不存在");
        }
        user.setEnabled(status);
        userMapper.updateById(user);
    }

    @Override
    public void deleteUserById(Long id, HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("admin_clims");
        if (claims==null){
            throw new BaseException(Status.AUTHORITY_FAILED,"权限不足");
        }
        userMapper.deleteById(id);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(!"".equals(username)&&username!=null,"username",username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Map<String,Object> login(String username, String password,Integer roleId) {
        User user = findUserByUsername(username);
        if (user==null){
            throw new BaseException(Status.FAILED,"用户名不存在！");
        }
        if (!user.getPassword().equals(password)){
            throw new BaseException(Status.FAILED,"用户名或密码错误！");
        }
        if (user.getRoleId()!=roleId){
            throw new BaseException(Status.FAILED,"角色类型错误！");
        }
        if (!user.getEnabled()){
            throw new BaseException(Status.FAILED,"账号被禁用，请联系管理员！");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("token", jwtUtil.createJWT(user,roleService.findRoleById(roleId)));
        map.put("user",user);
        return map;
    }

    @Override
    public User info(String token) {
        Claims claims = jwtUtil.parseJWT(token);
        return findUserByUsername((String) claims.get("userName"));
    }

}
