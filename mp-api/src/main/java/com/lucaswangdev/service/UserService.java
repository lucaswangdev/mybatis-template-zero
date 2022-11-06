package com.lucaswangdev.service;


import com.lucaswangdev.entity.User;

public interface UserService {
    // 更加id查询
    User selectById(Integer id);

    // 插入数据
    Integer insert(User user);

    // 更新操作
    Integer updateByPrimaryKeySelective(User user);

    // 删除数据
    Integer delete(Integer id);

}
