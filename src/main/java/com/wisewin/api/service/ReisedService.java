package com.wisewin.api.service;

import com.wisewin.api.dao.AppraisalDao;
import com.wisewin.api.dao.ReisedDAO;
import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.ReisedBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@Service
@Transactional
public class ReisedService {

    @Resource
    ReisedDAO reisedDAO;

    @Resource
    private AppraisalDao appraisalDao;

    public Integer addReisedDAO(ReisedBO reisedBO){
        AppraisalBo appraisalBo = appraisalDao.queryApprausalById(reisedBO.getAppraisalId());
        if(appraisalBo.getReisedState().equals("0") ||appraisalBo.getReisedState().equals("30") ){
            appraisalDao.updateAppraisalReisedState(reisedBO.getAppraisalId(),"10");
        }
        return reisedDAO.addReisedDAO(reisedBO);
    }
}
