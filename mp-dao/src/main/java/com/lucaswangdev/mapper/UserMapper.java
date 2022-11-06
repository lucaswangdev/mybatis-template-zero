package com.lucaswangdev.mapper;

import com.lucaswangdev.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends UserMapperBase {
    User query(@Param("id") Integer id);

    int delete(@Param("id") Integer id);
}

