package com.lucaswangdev.service.impl;

import com.lucaswangdev.entity.ApiMock;
import com.lucaswangdev.mapper.ApiMockMapper;
import com.lucaswangdev.service.ApiMockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiMockServiceImpl implements ApiMockService {
    @Autowired
    private ApiMockMapper apiMockMapper;

    @Override
    public Integer insert(ApiMock apiMock) {
        return apiMockMapper.insert(apiMock);
    }

    @Override
    public List<ApiMock> queryBySelective(ApiMock apiMock) {
        return apiMockMapper.queryBySelective(apiMock);
    }
}
