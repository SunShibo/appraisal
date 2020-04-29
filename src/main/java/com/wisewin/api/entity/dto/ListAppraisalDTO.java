package com.wisewin.api.entity.dto;

import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 11:21 2019/8/28
 */
public class ListAppraisalDTO {

    private List<AppraDTO> list;


    public List<AppraDTO> getList() {
        return list;
    }

    public void setList(List<AppraDTO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListAppraisalDTO{" +
                "list=" + list +
                '}';
    }
}
