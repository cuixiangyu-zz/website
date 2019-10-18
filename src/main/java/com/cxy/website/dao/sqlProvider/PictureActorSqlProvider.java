package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.PictureActor;
import org.apache.ibatis.jdbc.SQL;

public class PictureActorSqlProvider {

    public String insertSelective(PictureActor record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_picture_actor");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getPictureId() != null) {
            sql.VALUES("picture_id", "#{pictureId,jdbcType=INTEGER}");
        }
        
        if (record.getActorId() != null) {
            sql.VALUES("actor_id", "#{actorId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PictureActor record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_picture_actor");
        
        if (record.getPictureId() != null) {
            sql.SET("picture_id = #{pictureId,jdbcType=INTEGER}");
        }
        
        if (record.getActorId() != null) {
            sql.SET("actor_id = #{actorId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}