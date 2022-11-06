package com.lucaswangdev.controller;

import com.lucaswangdev.utils.AjaxResult;
import com.lucaswangdev.entity.User;
import com.lucaswangdev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @param
     * @return
     */
    @RequestMapping("/query")
    public AjaxResult findUser(@RequestBody User user) {
        try {
            User _user = userService.selectById(user.getId());
            return AjaxResult.success(_user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }


    @RequestMapping("/insert")
    public AjaxResult add(@RequestBody User user) {
        try {
            if (user.getUserName() == null || user.getSex() == null || user.getAddress() == null) {
                return AjaxResult.error("请求参数不正确");
            }
            user.setUserName(user.getUserName());
            user.setSex(user.getSex());
            user.setAddress(user.getAddress());
            Integer number = userService.insert(user);
            System.out.println("number=>" + number);
            return AjaxResult.success();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    @RequestMapping("/update")
    public AjaxResult update(@RequestBody User user) {
        try {
            if (user.getId() == null) {
                return AjaxResult.error("请求参数不正确");
            }
            if (user.getUserName() == null && user.getSex() == null && user.getAddress() == null) {
                return AjaxResult.error("请求参数不正确");
            }
            if(user.getUserName() != null) {
                user.setUserName(user.getUserName());
            }
            if(user.getSex() != null) {
                user.setSex(user.getSex());
            }
            if(user.getAddress() != null) {
                user.setAddress(user.getAddress());
            }
            Integer number = userService.updateByPrimaryKeySelective(user);
            System.out.println("update number=>" + number);
            return AjaxResult.success();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }


    /**
     * 删除方法
     * 待优化事务，需要回滚
     * @param user
     * @return
     */
    @RequestMapping("/delete")
    public AjaxResult delete(@RequestBody User user) {
        try {
            Integer integer = userService.delete(user.getId());
            return AjaxResult.success();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

}
