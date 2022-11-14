package com.lucaswangdev.mapper;

import com.lucaswangdev.entity.ApiMock;
import org.apache.ibatis.annotations.Param;

/**
 * ApiMockMapperBase.
 * auto generated by jasmine, please do not modify it!
 */
public interface ApiMockMapperBase{
    /**
     * physical delete
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") Long id);


    ApiMock selectByPrimaryKey(@Param("id") Long id);


    /**
     * insert entity
     * @param apiMock
     * @return
     */
    int insert(@Param("apiMock") ApiMock apiMock);

    /**
     * insert entity selective.
     * @param apiMock
     * @return
     */
    int insertSelective(@Param("apiMock") ApiMock apiMock);


    /**
     * update
     * @param apiMock
     * @return
     */
    int updateByPrimaryKey(@Param("apiMock") ApiMock apiMock);

    /**
     * update selective
     * @param apiMock
     * @return
     */
    int updateByPrimaryKeySelective(@Param("apiMock") ApiMock apiMock);
}
