package com.cxy.website.service;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.ViewHistory;

import java.util.Date;
import java.util.List;

public interface ViewHistoryService {

    /**
     * 添加浏览历史
     * @param viewHistory   浏览历史
     */
    void addHistory(ViewHistory viewHistory);

    /**
     * 查找浏览历史
     * @param type  类型
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return  浏览历史
     */
    JsonData getHistory(Integer pageNum, Integer pageSize , Integer type, String startTime, String endTime);
}
