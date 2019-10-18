package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.VideoActor;
import org.apache.ibatis.jdbc.SQL;

public class VideoActorSqlProvider {

    public String insertSelective(VideoActor record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_video_actor");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getVideoId() != null) {
            sql.VALUES("video_id", "#{videoId,jdbcType=INTEGER}");
        }
        
        if (record.getActorId() != null) {
            sql.VALUES("actor_id", "#{actorId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VideoActor record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_video_actor");
        
        if (record.getVideoId() != null) {
            sql.SET("video_id = #{videoId,jdbcType=INTEGER}");
        }
        
        if (record.getActorId() != null) {
            sql.SET("actor_id = #{actorId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}