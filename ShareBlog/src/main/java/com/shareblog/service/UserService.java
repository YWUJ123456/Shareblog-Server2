package com.shareblog.service;

import com.shareblog.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserService
 * @Author 杨武军
 * @Date 2020/5/18 13:31
 */
public interface UserService {
    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 修改用户状态
     * @param id
     * @param status
     */
    void changeStatus(Long id, Boolean status);

    /**
     * 删除用户
     * @param id
     */
    void deleteUserById(Long id, HttpServletRequest request);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 登录
     * @param username
     * @param password
     * @param role
     */
    Map<String,Object> login(String username, String password, Integer role);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    User info(String token);
}
