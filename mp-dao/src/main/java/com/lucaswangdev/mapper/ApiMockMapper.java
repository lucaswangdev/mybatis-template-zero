package com.lucaswangdev.mapper;

import com.lucaswangdev.entity.ApiMock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiMockMapper extends ApiMockMapperBase {
    List<ApiMock> queryBySelective(@Param("apiMock") ApiMock apiMock);
}

