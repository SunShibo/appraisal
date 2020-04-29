package com.wisewin.api.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoAuthRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import com.wisewin.api.entity.bo.common.constants.Result;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.util.AlipayConfig;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author: Wang bin
 * @date: Created in 13:03 2019/10/31
 */
@RequestMapping("/ali")
@Controller
public class AliLoginController extends BaseCotroller {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        return "看到这个就dui了";
    }


    @RequestMapping("/aliLogin")
    public void aliLogin(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);  //获得初始化的AlipayClient
        //AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
        SortedMap<String, String> map = new TreeMap<String, String>();
        map.put("apiname", "com.alipay.account.auth");
        map.put("method", "alipay.open.auth.sdk.code.get");
        map.put("app_id", AlipayConfig.APP_ID);
        map.put("app_name", "mc");
        map.put("biz_type", "openservice");
        map.put("pid", "2088221437132407");
        map.put("product_id","APP_FAST_LOGIN");
        map.put("scope","kuaijie");
        map.put("target_id", "kkkkk091125");
        map.put("auth_type","AUTHACCOUNT");
        map.put("sign_type","RSA2");
        String signStr = AlipaySignature.getSignContent(map);
        System.err.println(signStr);
        String sign = AlipaySignature.rsaSign(signStr, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.CHARSET);
        System.err.println(sign);
       // System.out.println(getEncodeSignContent(map) + "&sign=" + URLEncoder.encode(sign, 编码));
        try {
            String json = JsonUtils.getJsonString4JavaPOJO(new Result( signStr+"&sign="+URLEncoder.encode(sign,  AlipayConfig.CHARSET),"0000000","成功"));
            super.safeJsonPrint(response, json);
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
