package com.wisewin.api.service;

import com.wisewin.api.dao.FeedBackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Wang bin
 * @date: Created in 10:34 2019/10/8
 */
@Service
public class FeedBackService {

    @Autowired
    private FeedBackDAO feedBackDAO;

    public int insertFeedBack(Integer userId, String describc){
        return feedBackDAO.insertFeedBack(userId,describc);
    }
}
