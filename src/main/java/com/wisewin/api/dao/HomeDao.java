package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.BannerBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 首页
 * */
public interface HomeDao {
    //查询banner
    List<BannerBO> getBanner();
    //查询发布鉴定的数量
    Integer getAppraisalBoCount(Integer userId);
    //查询鉴定多少例
    List<Integer> getCommentBoCount(Integer userId);
    //分页查询鉴定案例
    List<AppraisalBo> getAppraisalBoList(@Param("pageOffset") Integer pageOffset,@Param("pageSize")Integer pageSize);
}
