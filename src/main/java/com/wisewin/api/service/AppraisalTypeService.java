package com.wisewin.api.service;

import com.wisewin.api.dao.AppraisalClassifyDao;
import com.wisewin.api.dao.AppraisalDao;
import com.wisewin.api.dao.AppraisalTypeDao;
import com.wisewin.api.entity.bo.AppraisalClassify;
import com.wisewin.api.entity.bo.AppraisalTypeBo;
import com.wisewin.api.entity.bo.common.constants.Result;
import com.wisewin.api.entity.param.AppraisalTypeParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 16:28 2019/8/27
 */
@Service
public class AppraisalTypeService {

    /*@Autowired
    private AppraisalTypeDao appraisalTypeDao;*/

    @Resource
    private AppraisalClassifyDao appraisalClassifyDao;

    /**
     * 获取商品信息列表
     * @return
     */
    public Result queryAppraisalType(){
       // return appraisalTypeDao.queryAppraisalType();
        List<AppraisalClassify> appraisalClassifyDaos = appraisalClassifyDao.selectAppraisalClassifyList();
        return new Result(appraisalClassifyDaos,"0000000" , "成功");
    }
}
