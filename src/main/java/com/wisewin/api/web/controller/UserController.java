package com.wisewin.api.web.controller;

import com.sun.xml.bind.v2.model.core.ID;
import com.wisewin.api.common.constants.UserConstants;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.bo.common.base.BaseModel;
import com.wisewin.api.entity.bo.common.constants.Result;
import com.wisewin.api.entity.bo.common.constants.SysConstants;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.service.UserService;
import com.wisewin.api.util.*;
import com.wisewin.api.util.date.DateUtil;
import com.wisewin.api.util.redisUtils.RedissonHandler;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller {
    static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 验证码注册登录 绑定openId
     * @param phone  手机号
     * @param code   验证码
     * @param type   类型 wx or qq
     * @param openid openid
     */
    @RequestMapping("/codeRegister")
    public void codeRegister(String phone, String code, String type, String openid, HttpServletResponse response, HttpServletRequest request) {
        try {
            //验证参数
            if (!(AccountValidatorUtil.isMobile(phone)) || code == null) {
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
                super.safeJsonPrint(response, json);
                return;
            }
            Map<String, Object> mapUser = new HashMap<String, Object>();
            //获取Redis中的用户验证码
            String mobileAuthCode = RedissonHandler.getInstance().get(phone + UserConstants.VERIFY.getValue());
            if (code.equals(mobileAuthCode)) {
                //删除验证码
                RedissonHandler.getInstance().delete(phone + UserConstants.VERIFY.getValue());
                //通过手机号查询表中是否有该用户
                UserBO userBO = userService.getUserByPhone(phone);
                if (userBO != null) {//登录
                    if (UserConstants.Yes.getValue().equals(userBO.getStatus())) {
                        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
                        super.safeJsonPrint(response, json);
                        return;
                    }
                    //绑定openId
                    if (type != null) {
                        UserBO userParam=new UserBO();
                        if (type.equals("qq")) {
                            userParam.setQqOpenid(openid);
                        } else {
                            userParam.setWxOpenid(openid);
                        }
                        userParam.setId(userBO.getId());
                        userService.updUserBo(userParam);
                    }

                    //user对象存入cookie and redis中
                    this.putUser(request,response, userBO);
                    mapUser.put("userId", userBO.getId());
                    mapUser.put("headUrl", userBO.getHeadUrl());
                    mapUser.put("wxId",userBO.getWxOpenid());
                    mapUser.put("qqId",userBO.getQqOpenid());
                    String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(mapUser));
                    super.safeJsonPrint(response, json);
                } else {//注册
                    //获取手机系统 注册渠道
                    UserBO userParam = new UserBO();
                    String model = request.getHeader("model");
                    userParam.setRegisteredChannels(model);
                    userParam.setName("用户_" + phone);
                    userParam.setPhone(phone);
                    if (type != null) {
                        if (type.equals("qq")) {
                            userParam.setQqOpenid(openid);
                        } else {
                            userParam.setWxOpenid(openid);
                        }
                    }
                    userService.addUserBo(userParam);
                    UserBO userResult = userService.getUserByPhone(phone);
                    this.putUser(request,response, userResult);
                    mapUser.put("userId", userParam.getId());
                    mapUser.put("headUrl", userParam.getHeadUrl());
                    mapUser.put("wxId",userResult.getWxOpenid());
                    mapUser.put("qqId",userResult.getQqOpenid());
                    String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(mapUser));
                    super.safeJsonPrint(response, json);
                    return;
                }
            } else {
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
                super.safeJsonPrint(response, json);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将user对象存入到Cookie
     */
    private void putUser(HttpServletRequest request,HttpServletResponse response, UserBO userBO) {
        String uuid = UUID.randomUUID().toString();
        super.putRedisLoginUser(uuid, userBO);
        //super.removeCookie(request,response,SysConstants.CURRENT_LOGIN_CLIENT_ID);
        super.setCookie(response, SysConstants.CURRENT_LOGIN_CLIENT_ID, uuid, 24 * 60 * 60 * 30);
        //不知道作用先注了
        Object oldKey = RedissonHandler.getInstance().get(userBO.getId() + SysConstants.LOGIN_IDENTIFICATION);
        if(oldKey!=null){
            RedissonHandler.getInstance().delete((String)oldKey);
        }

        RedissonHandler.getInstance().set(userBO.getId()+ SysConstants.LOGIN_IDENTIFICATION,
                super.createKey(uuid, com.wisewin.api.common.constants.SysConstants.CURRENT_LOGIN_USER) , (long)24*60*60*30);
//
    }

    /**
     * 发送验证码
     *
     * @param type 发送验证码类型 amendPhone为修改绑定手机号
     */
    @RequestMapping("/send")
    public void send(String phone, String type, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        if (!(AccountValidatorUtil.isMobile(phone))) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        //修改绑定 验证手机号
        if ("amendPhone".equals(type) && userService.getUserByPhone(phone) != null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000005"));
            super.safeJsonPrint(response, json);
            return;
        }
        //获取缓存中的失效验证码
        String mobileAuthCode = RedissonHandler.getInstance().get(phone + UserConstants.VERIFY_LOSE.getValue());
        if (mobileAuthCode != null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeJsonPrint(response, json);
            return;
        }
        //验证码次数
        String count = RedissonHandler.getInstance().get(phone + UserConstants.DEGREE.getValue());
        if (count == null) {
            count = "0";
        }
        Integer countInteger = new Integer(count);
        /*if (countInteger > 20) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000006"));
            super.safeJsonPrint(response, json);
            return;
        }*/
        userService.send(phone);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("短信验证码发送成功!"));
        super.safeJsonPrint(response, json);
    }

    /**
     * 扣扣and 微信登陆
     *
     * @param openid 为微信账号 或者qq号
     * @param type   wx或者qq
     */
    @RequestMapping("/openidLogin")
    public void openidLogin(HttpServletRequest request, HttpServletResponse response,
                            String openid, String type) {
        //参数异常
        if (openid == null || type == null || openid.equals("") || type.equals("")) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        //获取到和openid绑定的手机号
        UserBO user = userService.getPhoneByOpenId(openid, type);
        if (user == null || user.getPhone() == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000007"));
            super.safeJsonPrint(response, json);
            return;
        }
        //返回给前端的数据
        Map<String, Object> mapUser = new HashMap<String, Object>();
        UserBO userBO = userService.getUserByPhone(user.getPhone());
        if (UserConstants.Yes.getValue().equals(userBO.getStatus())) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeJsonPrint(response, json);
            return;
        }
        //user对象存入cookie中
        this.putUser(request,response, userBO);
        mapUser.put("userId", userBO.getId());
        mapUser.put("headUrl", userBO.getHeadUrl());
        mapUser.put("wxId",userBO.getWxOpenid());
        mapUser.put("qqId",userBO.getQqOpenid());
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(mapUser));
        super.safeJsonPrint(response, json);
        return;
    }

    /**
     * 解除绑定
     */
    @RequestMapping("/removeOpenid")
    public void removeOpenid(String type, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中获取他的user对象的id
        Integer id = super.getId(request);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        //判断参数
        if (StringUtils.isEmpty(type)) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        userService.removeOpenId(type, id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(null));
        super.safeJsonPrint(response, json);
    }

    /**
     * 修改用户信息
     *
     * @param response
     * @param request
     */
    @RequestMapping("/updateUserInfo")
    public void updateUserInfo(UserBO userBO, HttpServletResponse response, HttpServletRequest request) {
        Integer id = this.getId(request);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        //如果修改绑定的微信或者qq 检查这个openid是否已经被绑定
        String qqId = userBO.getQqOpenid();
        if (!StringUtils.isEmpty(qqId)) {
            UserBO user = userService.getPhoneByOpenId(qqId, "qq");
            if (user != null && user.getPhone() != null && user.getId() != id) {
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000009"));
                super.safeJsonPrint(response, json);
                return;
            }
        }
        String wxId = userBO.getWxOpenid();
        if (!StringUtils.isEmpty(wxId)) {
            UserBO user = userService.getPhoneByOpenId(wxId, "wx");
            if (user != null && user.getPhone() != null && user.getId() != id) {
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000009"));
                super.safeJsonPrint(response, json);
                return;
            }
        }
        //把id设置到user参数对象中
        userBO.setId(id);
        userService.updUserBo(userBO);
//        //重新加载到redis
//        this.putUser(request,response, userBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(null));
        super.safeJsonPrint(response, json);
    }

    /**
     * 查询用户信息
     */
    @RequestMapping("/getCurrUser")
    public void getCurrUser(HttpServletResponse response, HttpServletRequest request) {
        //从cookie中获取他的user对象的id
        UserBO user = this.getLoginUser(request);
        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        //通过id找出用户对象
        UserBO userBO = userService.getUserByPhone(user.getPhone());
        log.info("userbO:{}", userBO);
        Map<String, Object> mapUser = new HashMap<String, Object>();
        mapUser.put("id", userBO.getId());
        mapUser.put("name", userBO.getName());
        mapUser.put("phone", userBO.getPhone());
        mapUser.put("headUrl", userBO.getHeadUrl());
        mapUser.put("integral", userBO.getIntegral());
        mapUser.put("money", userBO.getMoney());
        mapUser.put("idNumber", userBO.getIdNumber());
        mapUser.put("birthday", userBO.getBirthday());
        mapUser.put("email", userBO.getEmail());
        mapUser.put("sex", userBO.getSex());
        mapUser.put("qqId", userBO.getQqOpenid());
        mapUser.put("wxId", userBO.getWxOpenid());
        mapUser.put("watermark", userBO.getWatermark());
        mapUser.put("watermarkState", userBO.getWatermarkState());
        mapUser.put("zfbUserId", userBO.getZfbUserId());
        log.info("payPassword:{}",userBO.getPayPassword());
        if(userBO.getPayPassword() == "" || userBO.getPayPassword() == null || "".equals(userBO.getPayPassword())){
            mapUser.put("payPassword", false);
        } else{
            mapUser.put("payPassword", true);
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(mapUser));
        super.safeJsonPrint(response, json);
    }


    /**
     * 修改绑定手机号
     *
     * @param code 用户验证码
     */
    @RequestMapping("/updateUserPhone")
    public void updateUserPhone(String phone, String code, HttpServletResponse response, HttpServletRequest request) {
        UserBO loginUser = super.getLoginUser(request);
        //验证参数
        if(loginUser == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        //验证参数
        if (!(AccountValidatorUtil.isMobile(phone)) || code == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        //获取Redis中的用户验证码
        String mobileAuthCode = RedissonHandler.getInstance().get(phone + UserConstants.VERIFY.getValue());
        if (code.equals(mobileAuthCode)) {
            //获取当前登陆用户
            Integer id = loginUser.getId();
            UserBO userBO = new UserBO();
            userBO.setPhone(phone);
            userBO.setId(id);
            userService.updUserBo(userBO);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(null));
            super.safeJsonPrint(response, json);
        } else {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeJsonPrint(response, json);
        }
    }

    /**
     * 上传头像
     * @param response
     * @param request
     */
    @RequestMapping("/upPicture")
    public void upPicture(HttpServletResponse response, HttpServletRequest request, MultipartFile file)
            throws Exception {
        //从cookie中获取他的user对象的id
        Integer id = this.getId(request);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        //图片非空判断
        if (file == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        OSSClientUtil ossClientUtil = new OSSClientUtil();
        //上传
        String url = ossClientUtil.uploadImg2Oss(file);
        UserBO userBO=new UserBO();
        userBO.setId(id);
        userBO.setHeadUrl(url);
        userService.updUserBo(userBO);
        Map<String, Object> mapUser = new HashMap<String, Object>();
        mapUser.put("headUrl", url);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(mapUser));
        super.safeJsonPrint(response, json);
    }


    /**
     * 设置支付密码
     * @param request
     * @param response
     */
    @RequestMapping("/payPassword")
    public void payPassword(HttpServletRequest request, HttpServletResponse response, String payPassword){
        UserBO loginUser = super.getLoginUser(request);
        int i = userService.updateUserPayPassword(loginUser.getId(), payPassword);
        if(i > 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000010"));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     * 短信验证码修改支付密码
     * @param request
     * @param response
     */
    @RequestMapping("/savePayPasswordSend")
    public void savePayPasswordSend(HttpServletRequest request, HttpServletResponse response){
        UserBO loginUser = super.getLoginUser(request);
        //获取用户手机号  生成验证码  调用短信服务
        userService.savePayPassword(loginUser.getPhone());
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("发送成功"));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     * 更改支付密码 校验修改密码
     * @param request
     * @param response
     * @param number
     */
    @RequestMapping("/savePayPassword")
    public void savePayPassword(HttpServletRequest request, HttpServletResponse response, String number, String newPayPassword){
        UserBO loginUser = super.getLoginUser(request);
        String mobileAuthCode = RedissonHandler.getInstance().get(loginUser.getPhone() + "pay_passowrd");
        System.err.println(mobileAuthCode);
        if(StringUtils.isEmpty(newPayPassword)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000010"));
            super.safeJsonPrint(response, json);
            return;
        }
        if(mobileAuthCode.equals(number)){
            userService.updateUserPayPassword(loginUser.getId(), newPayPassword);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("成功"));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000011"));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     * 校验支付密码
     * @param request
     * @param response
     */
    @RequestMapping("/checkPaymentPassword")
    public void checkPaymentPassword(HttpServletRequest request, HttpServletResponse response,
                                      String password){
        UserBO loginUser = super.getLoginUser(request);
        //获取用户的支付密码拿出用户支付密码判断
        Result result = userService.checkPaymentPassword(loginUser.getId(), password);
        String jsonString4JavaPOJO = JsonUtils.getJsonString4JavaPOJO(result);
        super.safeJsonPrint(response, jsonString4JavaPOJO);
        return;
    }


    /**
     * 添加用户支付宝userid
     * @param request
     * @param response
     * @param aliUserId
     */
    @RequestMapping("/bindingAliPay")
    public void bindingAliPay(HttpServletRequest request, HttpServletResponse response, String aliUserId){
        UserBO loginUser = super.getLoginUser(request);
        Result result = userService.bindingAliPay(loginUser.getId(), aliUserId);
        String jsonString4JavaPOJO = JsonUtils.getJsonString4JavaPOJO(result);
        super.safeJsonPrint(response, jsonString4JavaPOJO);
        return;
    }

    /**
     * 解绑
     * @param request
     * @param response
     */
    @RequestMapping("/relieveAliPay")
    public void relieveAliPay(HttpServletRequest request, HttpServletResponse response){
        UserBO loginUser = super.getLoginUser(request);
        Result result = userService.bindingAliPay(loginUser.getId(), null);
        String jsonString4JavaPOJO = JsonUtils.getJsonString4JavaPOJO(result);
        super.safeJsonPrint(response, jsonString4JavaPOJO);
        return;
    }


    /**
     * 设置\申请水印
     * @param request
     * @param response
     * @param watermark
     */
    @RequestMapping("/setwatermark")
    public void setwatermark(HttpServletRequest request, HttpServletResponse response, String  watermark){
        UserBO loginUser = super.getLoginUser(request);
        Result setwatermark = userService.setwatermark(loginUser.getId(), watermark);
        String jsonString4JavaPOJO = JsonUtils.getJsonString4JavaPOJO(setwatermark);
        super.safeJsonPrint(response, jsonString4JavaPOJO);
        return;
    }


}
