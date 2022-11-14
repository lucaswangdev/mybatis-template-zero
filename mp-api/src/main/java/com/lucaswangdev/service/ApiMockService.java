package com.lucaswangdev.service;

import com.lucaswangdev.entity.ApiMock;
import java.util.List;

public interface ApiMockService {
    // 插入 mock 接口
    Integer insert(ApiMock apiMock);

    // 查询 mock 接口
    List<ApiMock> queryBySelective(ApiMock apiMock);
}
