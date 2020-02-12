package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.ViewHistory;
import org.apache.ibatis.jdbc.SQL;

public class ViewHistorySqlProvider {

    public String insertSelective(ViewHistory record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_view_history");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getVideoId() != null) {
            sql.VALUES("video_id", "#{videoId,jdbcType=INTEGER}");
        }
        
        if (record.getStartData() != null) {
            sql.VALUES("start_data", "#{startData,jdbcType=TIMESTAMP}");
        }
        
        if (record.getWatchTime() != null) {
            sql.VALUES("watch_time", "#{watchTime,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ViewHistory record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_view_history");
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getVideoId() != null) {
            sql.SET("video_id = #{videoId,jdbcType=INTEGER}");
        }
        
        if (record.getStartData() != null) {
            sql.SET("start_data = #{startData,jdbcType=TIMESTAMP}");
        }
        
        if (record.getWatchTime() != null) {
            sql.SET("watch_time = #{watchTime,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}