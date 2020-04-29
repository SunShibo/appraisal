package com.wisewin.api.web.controller;

import com.wisewin.api.entity.bo.ReisedBO;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.service.ReisedService;
import com.wisewin.api.service.UserService;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.util.OSSClientUtil;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reised")
public class ReisedController extends BaseCotroller {
    static final Logger log = LoggerFactory.getLogger(BaseCotroller.class);
    @Resource
    private ReisedService reisedService;

    /**
     * 纠错
     */
    @RequestMapping("/addReised")
    public void upPicture(HttpServletResponse response, HttpServletRequest request, ReisedBO reisedBO)
            throws Exception {
        //从cookie中获取他的user对象的id
        Integer id = this.getId(request);
        //如果获取不到,参数异常
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        if (reisedBO == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        reisedBO.setUserId(id);
        reisedService.addReisedDAO(reisedBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(null));
        super.safeJsonPrint(response, json);
    }
}
