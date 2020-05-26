package com.shareblog.controller;

import com.shareblog.response.MsgResponse;
import com.shareblog.response.Status;
import com.shareblog.utils.MsgResponseUtil;
import com.shareblog.bean.User;
import com.shareblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Author 杨武军
 * @Date 2020/5/18 13:29
 */
@RestController
@Api(description = "用户相关接口")
@RequestMapping("/manager/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAllUser")
    @ApiOperation("查询所有用户")
    public MsgResponse findAllUser(){
        return MsgResponseUtil.findSucess(userService.findAllUser());
    }

    @PostMapping("/saveUser")
    @ApiOperation("新增用户")
    public MsgResponse saveUser(User user){
        userService.saveUser(user);
        return MsgResponseUtil.addSucess();
    }

    @PutMapping("/updateUser")
    @ApiOperation("更新用户")
    public MsgResponse updateUser(User user){
        userService.updateUser(user);
        return MsgResponseUtil.updateSucess();
    }

    @PutMapping("/changeStatus")
    @ApiOperation("更改用户状态")
    public MsgResponse changeStatus(Long id,Boolean status){
        userService.changeStatus(id,status);
        return MsgResponseUtil.updateSucess();
    }

    @DeleteMapping("/deleteUserById")
    @ApiOperation("通过id删除用户")
    public MsgResponse deleteUserById(Long id,HttpServletRequest request){
        userService.deleteUserById(id,request);
        return MsgResponseUtil.deleteSucess();
    }

    @GetMapping("/findUserById")
    @ApiOperation("通过id查询用户")
    public MsgResponse findUserById(Long id){
        return MsgResponseUtil.findSucess(userService.findUserById(id));
    }

    @GetMapping("/findUserByUsername")
    @ApiOperation("通过用户名查询用户")
    public MsgResponse findUserByUsername(String username){
        return MsgResponseUtil.findSucess(userService.findUserByUsername(username));
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public MsgResponse login(String username,String password,Integer roleId){
        return new MsgResponse(Status.SUCCESS,"登录成功",userService.login(username,password,roleId));
    }

    @GetMapping("/info")
    @ApiOperation("根据token获取用户信息")
    public MsgResponse info(String token){
        return new MsgResponse(Status.SUCCESS,"获取成功",userService.info(token));
    }
}
