package com.wisewin.api.service;

import com.wisewin.api.dao.HomeDao;
import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.BannerBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class HomeService {
    @Resource
    HomeDao homeDao;

    //查询banner
    public List<BannerBO> getBanner(){
        return homeDao.getBanner();
    }
    //查询发布鉴定的数量
    public Integer getAppraisalBoCount(Integer userId){
        return homeDao.getAppraisalBoCount(userId);
    }
    //查询鉴定多少例
    public Integer getCommentBoCount(Integer userId){
        if(homeDao.getCommentBoCount(userId)==null){
            return 0;
        }
        return homeDao.getCommentBoCount(userId).size();
    }
    //分页查询鉴定案例
    public List<AppraisalBo> getAppraisalBoList(Integer pageOffset,Integer size){
        return homeDao.getAppraisalBoList(pageOffset,size);
    }
}
