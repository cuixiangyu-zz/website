package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.Video;
import org.apache.ibatis.jdbc.SQL;

public class VideoSqlProvider {

    public String insertSelective(Video record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_video");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getActor() != null) {
            sql.VALUES("actor", "#{actor,jdbcType=INTEGER}");
        }
        
        if (record.getVideoUrl() != null) {
            sql.VALUES("video_url", "#{videoUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCoverUrl() != null) {
            sql.VALUES("cover_url", "#{coverUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatTime() != null) {
            sql.VALUES("creat_time", "#{creatTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.VALUES("creater", "#{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getExist() != null) {
            sql.VALUES("exist", "#{exist,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Video record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_video");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getActor() != null) {
            sql.SET("actor = #{actor,jdbcType=INTEGER}");
        }
        
        if (record.getVideoUrl() != null) {
            sql.SET("video_url = #{videoUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCoverUrl() != null) {
            sql.SET("cover_url = #{coverUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatTime() != null) {
            sql.SET("creat_time = #{creatTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.SET("creater = #{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getExist() != null) {
            sql.SET("exist = #{exist,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}