package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.CommentBO;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.AppraisalDTO;
import com.wisewin.api.entity.param.AppraisalParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppraisalDao {

    int deleteByPrimaryKey(Integer id);

    int insert(AppraisalBo record);

    int insertSelective(AppraisalBo record);

    AppraisalBo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppraisalBo record);

    int updateByPrimaryKeyWithBLOBs(AppraisalBo record);

    int updateByPrimaryKey(AppraisalBo record);

    /**
     * 用户发鉴定
     * @param
     * @return
     */
    int insertAppraisal(AppraisalParam appraisalParam);

    /**
     * 插入鉴定图片
     * @param
     * @param
     * @return
     */
    int insertAppraisalImages(List<Map<String,Object>> array);

    /**
     * 鉴定列表
     * @param map
     * @return
     */
    List<AppraisalDTO> appraisalList(Map<String, Object> map);

    /**
     * 查询记录数
     * @param map
     * @return
     */
    int appraisalCount(Map<String,Object> map);

    /**
     * 获取鉴定详情
     * @param appraisalId
     * @return
     */
    AppraisalBo queryApprausalById(@Param("appraisalId")Integer appraisalId);

    /**
     * 修改鉴定状态
     * @param appraisalId
     * @return
     */
    int updateAppraisalState(@Param("appraisalId")Integer appraisalId, @Param("appraisalState")String appraisalState);

    UserBO getUserById(@Param("id")Integer id);

    AppraisalBo isAppraisal(@Param("userId")Integer userId, @Param("id")Integer id);

    /**
     * 获取评论
     * @param map
     * @return
     */
    List<CommentBO> queryComment(Map<String, Object> map);


    /**
     * 获取被采纳数
     * @param integral
     * @return
     */
    Integer queryIntegral(@Param("integral")Integer integral);


    /**
     * 获取被采纳的评论
     * @param map
     * @return
     */
    CommentBO goodsStateComment(Map<String, Object> map);


    /**
     * 根据用户id获取用户发布的鉴定
     * @param userId
     * @return
     */
    List<AppraisalBo> listAppraisalByUserId(@Param("userId")Integer userId);


    /**
     * 获取用户评论的鉴定id
     * @param userId
     * @return
     */
    List<Integer> queryAppraisalIdByComment(Integer userId);


    List<AppraisalBo> selectAppraisalOnId(@Param("list") List<Integer> list);


    /**
     * 修改鉴定纠错状态
     * @param id
     * @param reisedState
     * @return
     */
    int updateAppraisalReisedState(@Param("id")Integer id, @Param("reisedState")String reisedState);
}