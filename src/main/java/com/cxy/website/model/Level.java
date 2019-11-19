package com.cxy.website.model;

import java.io.Serializable;

public class Level implements Serializable {
    private Integer id;

    private Integer productionId;

    private String level;

    private Integer userId;

    private Integer productionType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductionType() {
        return productionType;
    }

    public void setProductionType(Integer productionType) {
        this.productionType = productionType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}