package com.wisewin.api.entity.bo;

/**
 * @Author: Wang bin
 * @date: Created in 14:01 2019/10/30
 */
public class AppraisalClassifyInfo {
    private Integer id;
    private String name;
    private Integer typeId;
    private String pictureUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "AppraisalClassifyInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
