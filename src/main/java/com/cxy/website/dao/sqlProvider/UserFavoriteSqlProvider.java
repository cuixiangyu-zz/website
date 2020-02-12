package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.UserFavorite;
import org.apache.ibatis.jdbc.SQL;

public class UserFavoriteSqlProvider {

    public String insertSelective(UserFavorite record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_user_favorite");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getVideoId() != null) {
            sql.VALUES("video_id", "#{videoId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatTime() != null) {
            sql.VALUES("creat_time", "#{creatTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserFavorite record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_user_favorite");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getVideoId() != null) {
            sql.SET("video_id = #{videoId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatTime() != null) {
            sql.SET("creat_time = #{creatTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}