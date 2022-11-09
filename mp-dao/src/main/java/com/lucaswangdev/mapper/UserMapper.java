package com.lucaswangdev.mapper;

import com.lucaswangdev.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends UserMapperBase {
    User query(@Param("id") Integer id);

    int delete(@Param("id") Integer id);

    /**
     * 通过地址查询用户(分页)
     * @param user
     * @return
     */
    List<User> queryByAddress(@Param("user") User user);
}

