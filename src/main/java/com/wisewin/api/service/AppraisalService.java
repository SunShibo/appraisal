package com.wisewin.api.service;

import com.wisewin.api.dao.AppraisalDao;
import com.wisewin.api.dao.CommentDao;
import com.wisewin.api.dao.UserCenterDao;
import com.wisewin.api.dao.UserDao;
import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.CommentBO;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.*;
import com.wisewin.api.entity.param.AppraisalParam;
import com.wisewin.api.util.env.Env;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * @Author: Wang bin
 * @date: Created in 12:38 2019/8/27
 */
@Service
public class AppraisalService {

    final static Logger log =  LoggerFactory.getLogger(AppraisalService.class);

    @Autowired
    private AppraisalDao appraisalDao;

    @Autowired
    private UserCenterDao userCenterDao;

    @Autowired
    private CommentDao commentDao;

    @Resource
    private UserDao userDao;

    public void insertAppraisal(AppraisalParam appraisalParam){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        appraisalDao.insertAppraisal(appraisalParam);
    }

    /**
     * 鉴定信息列表
     * @param map
     * @return
     */
    public List<AppraDTO> queryListAppraisal(Map<String, Object> map){

        List<AppraisalDTO> appraisalDTOS =  appraisalDao.appraisalList(map);

        List<AppraDTO> ls = new ArrayList<AppraDTO>();
        if(CollectionUtils.isNotEmpty(appraisalDTOS)){
            for (AppraisalDTO appraisalDTO : appraisalDTOS) {
                AppraDTO appraDTO = new AppraDTO();
                String typeName = appraisalDTO.getAppraisalTypeName();
                String title = appraisalDTO.getTitle();
                appraDTO.setTitle("#"+typeName+"#"+title);
                appraDTO.setDescribc(appraisalDTO.getDescribc());
                appraDTO.setAppraisalState(appraisalDTO.getAppraisalState());
                appraDTO.setApImages(appraisalDTO.getApImages());
                appraDTO.setCreateTime(appraisalDTO.getCreateTime().substring(0,10));
                appraDTO.setId(appraisalDTO.getId());
                appraDTO.setAppraisalTypeId(appraisalDTO.getAppraisalTypeId());
                ls.add(appraDTO);
            }
        }
        int i = appraisalDao.appraisalCount(map);

        return ls;
    }

    /**
     * 获取鉴定详情
     * @param appraisalId
     * @return
     */
    public Map<String, Object> queryAppraisalById(Integer appraisalId, Integer userId){
        Env env = new Env();
        AppraisalBo appraisalBo = appraisalDao.queryApprausalById(appraisalId);
        System.err.println(appraisalBo);
        AppraisalDetaliDTO appraisalDetaliDTO = new AppraisalDetaliDTO();
        if(appraisalBo == null){
           return null;
        }
        org.springframework.beans.BeanUtils.copyProperties(appraisalBo, appraisalDetaliDTO);
        System.err.println(appraisalDetaliDTO);
        Map<String, Object> map = new HashMap<String, Object>();
        String apImages = appraisalBo.getApImages();
        //将图片取出按照逗号分隔放到list里面
        List<String> strings = Arrays.asList(apImages.split(","));
        appraisalDetaliDTO.setTitle("#"+appraisalDetaliDTO.getAppraisalTypeName()+"#"+appraisalDetaliDTO.getTitle());
        map.put("appraisal",appraisalDetaliDTO);
        map.put("images",strings);
        //http://localhost:8080/download.html?appraisalId=1&state=no
        //http://39.106.54.2:8080/download.html?appraisalId=1&state=no
        String state;
        if(appraisalBo.getUserId().equals(userId))
        {
            state = "yes";
        } else {
            state = "no";
        }
        map.put("domainName",env.getProperty("domain_name")+"/download.html?appraisalId="+appraisalId+"&state="+state);


        Map<String, Object> mp =  new HashMap<String, Object>();
        mp.put("appraisalId", appraisalId);
        List<CommentBO> commentBOS = appraisalDao.queryComment(mp);
        ArrayList<CommentBO> commentBOS1 = removeDuplicate(commentBOS);
        List<String> headUrl = new ArrayList<String>();
        for (CommentBO commentBO : commentBOS1) {
            headUrl.add(commentBO.getHeadUrl());
        }
        map.put("url",headUrl);

        //处理时间类型
        appraisalDetaliDTO.setCreateTime(appraisalDetaliDTO.getCreateTime().substring(0,19));
        appraisalDetaliDTO.setUpdateTime(appraisalDetaliDTO.getUpdateTime().substring(0,19));
        //判断用户是否收藏过此鉴定
        Integer enshrineByUserIdAndAppraisalId = userCenterDao.getEnshrineByUserIdAndAppraisalId(userId, appraisalId);
        if(enshrineByUserIdAndAppraisalId > 0){
            map.put("ollection", "yes"); //收藏过
        } else {
            map.put("ollection", "no");//未收藏
        }
        //判断是否有采纳的评论
        CommentBO commentBO = commentDao.queryCommentByAppraisalId(appraisalId);

        if(commentBO != null){
            UserBO byId = userDao.getById(commentBO.getUserId());
            //有被采纳的评论
            map.put("adopt", "yes");
            map.put("watermark", byId.getWatermark());
            map.put("watermarkState", byId.getWatermarkState());
            if(commentBO.getJudge().equals("genuine")){
                map.put("judge", "genuine");
            } else {
                map.put("judge", "counterfeit");
            }
        } else {
            //没有被采纳的评论
            map.put("adopt", "no");

        }

        return map;
    }

    public  Map<String, Object> share(Integer appraisalId, String state){

        Map<String, Object> map = new HashMap<String, Object>();
        AppraisalBo appraisalBo = appraisalDao.queryApprausalById(appraisalId);
        if(appraisalBo == null){
            return null ;
        }

        String apImages = appraisalBo.getApImages();
        AppraisalDetaliDTO appraisalDetaliDTO = new AppraisalDetaliDTO();
        org.springframework.beans.BeanUtils.copyProperties(appraisalBo, appraisalDetaliDTO);
        if(state.equals("yes")){
            map.put("head","我刚发布的"+appraisalBo.getAppraisalTypeName()+",快帮我看看");
        } else {
            map.put("head", "发现了一个好东西，快来围观");
        }
        appraisalDetaliDTO.setCreateTime(appraisalDetaliDTO.getCreateTime().substring(0,19));
        //将图片取出按照逗号分隔放到list里面
        List<String> strings = Arrays.asList(apImages.split(","));
        appraisalDetaliDTO.setTitle("#"+appraisalDetaliDTO.getAppraisalTypeName()+"#"+appraisalDetaliDTO.getTitle());
        map.put("appraisal",appraisalDetaliDTO);
        map.put("images",strings);
        Map<String, Object> mp =  new HashMap<String, Object>();
        mp.put("appraisalId", appraisalId);
        List<CommentBO> commentBOS = appraisalDao.queryComment(mp);
        ArrayList<CommentBO> commentBOS1 = removeDuplicate(commentBOS);
        List<String> headUrl = new ArrayList<String>();
        for (CommentBO commentBO : commentBOS1) {
            headUrl.add(commentBO.getHeadUrl());
        }
        map.put("url",headUrl);
        return map;
    }


    private static ArrayList<CommentBO> removeDuplicate(List<CommentBO> users) {
        Set<CommentBO> set = new TreeSet<CommentBO>(new Comparator<CommentBO>() {
            @Override
            public int compare(CommentBO o1, CommentBO o2) {
                //字符串,则按照asicc码升序排列
                return o1.getUserId().compareTo(o2.getUserId());
            }
        });
        set.addAll(users);
        return new ArrayList<CommentBO>(set);
    }


    /**
     * 获取我的发布
     * @param userId
     * @return
     */
    public List<UserAppraisalDTO> queryAppraisal(Integer userId){
        List<AppraisalBo> appraisalBos = appraisalDao.listAppraisalByUserId(userId);
        List<UserAppraisalDTO> list = new ArrayList<UserAppraisalDTO>();
        for (AppraisalBo appraisalBo : appraisalBos) {
            UserAppraisalDTO dto = new UserAppraisalDTO();
            dto.setId(appraisalBo.getId());
            dto.setUserId(appraisalBo.getUserId());
            dto.setDescribc(appraisalBo.getDescribc());
            String apImages = appraisalBo.getApImages();
            List<String> strings = Arrays.asList(apImages.split(","));
            dto.setApImages(strings.get(0));
            dto.setAppraisalState(appraisalBo.getAppraisalState());
            dto.setStatus(appraisalBo.getStatus());
            dto.setTitle("#"+appraisalBo.getAppraisalTypeName()+"#"+appraisalBo.getTitle());
            dto.setCreateTime(appraisalBo.getCreateTime().substring(0,19));
            list.add(dto);
        }
        return list;
    }


    /**
     * 获取我的鉴定
     * @param userId
     * @return
     */
    public List<IAppraisalDTO> queryIAppraisal(Integer userId){
        List<Integer> integers = appraisalDao.queryAppraisalIdByComment(userId);
        log.info("去重前:{}",integers);
        List<IAppraisalDTO> list = new ArrayList<IAppraisalDTO>();
        //去重
        List<Integer> inte =  new ArrayList<Integer>(new LinkedHashSet<Integer>(integers));
        log.info("去重后:{}",inte);
        if(!CollectionUtils.isEmpty(inte)){
            log.info("进入判断");
            List<AppraisalBo> appraisalBos = appraisalDao.selectAppraisalOnId(inte);
            log.info("appraisalBos:{}",appraisalBos);
            for (AppraisalBo appraisalBo : appraisalBos) {
                IAppraisalDTO dto = new IAppraisalDTO();
                dto.setId(appraisalBo.getId());
                dto.setUserId(appraisalBo.getUserId());
                dto.setDescribc(appraisalBo.getDescribc());
                String apImages = appraisalBo.getApImages();
                List<String> strings = Arrays.asList(apImages.split(","));
                dto.setApImages(strings.get(0));
                dto.setAppraisalState(appraisalBo.getAppraisalState());
                dto.setStatus(appraisalBo.getStatus());
                dto.setTitle("#"+appraisalBo.getAppraisalTypeName()+"#"+appraisalBo.getTitle());
                dto.setCreateTime(appraisalBo.getCreateTime().substring(0,19));
                list.add(dto);
            }
            log.info("return:{}",list);
            return list;
        }
        log.info("返回:{}",list);
        return list;
    }

}
