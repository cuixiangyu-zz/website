package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.VideoType;
import org.apache.ibatis.jdbc.SQL;

public class VideoTypeSqlProvider {

    public String insertSelective(VideoType record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_video_type");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getVideoId() != null) {
            sql.VALUES("video_id", "#{videoId,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            sql.VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VideoType record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_video_type");
        
        if (record.getVideoId() != null) {
            sql.SET("video_id = #{videoId,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            sql.SET("type_id = #{typeId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}