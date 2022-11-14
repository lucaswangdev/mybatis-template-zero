package com.lucaswangdev.controller;

import com.alibaba.fastjson2.JSONObject;
import com.lucaswangdev.entity.ApiMock;
import com.lucaswangdev.service.ApiMockService;
import com.lucaswangdev.utils.AjaxResult;
import com.lucaswangdev.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping()
public class ApiMockController extends BaseController {
    @Autowired
    private ApiMockService apiMockService;

    /**
     * @param
     * @return
     */
    @RequestMapping("/insert")
    public AjaxResult findUser(@RequestBody ApiMock apiMock) {
        if (apiMock.getApiPath() == null || apiMock.getApiContent() == null) {
            return AjaxResult.error("请求参数不正确");
        }
        try {
            apiMock.setApiPath(apiMock.getApiPath());
            apiMock.setApiContent(apiMock.getApiContent());
            if(apiMock.getDelay() != null && apiMock.getDelay() > 0) {
                apiMock.setDelay(apiMock.getDelay());
            } else {
                apiMock.setDelay(0);
            }
            apiMockService.insert(apiMock);
            return AjaxResult.success();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * @param
     * @return
     */
    @RequestMapping("/mock/**")
    public AjaxResult queryBySelective(@RequestBody ApiMock apiMock, HttpServletRequest request) {
        try {
            String servletPath = request.getServletPath();
            String _apiPath = servletPath.replace("/mock/", "");
            apiMock.setApiPath(_apiPath);
            List<ApiMock> apiMockList = apiMockService.queryBySelective(apiMock);
            if(apiMockList.size() > 0) {
                // json 字符串
                String jsonString = apiMockList.get(0).getApiContent();
                // json 字符串转为 json 对象
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return AjaxResult.success(jsonObject);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

}
