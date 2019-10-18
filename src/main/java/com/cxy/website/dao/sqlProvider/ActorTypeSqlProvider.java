package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.ActorType;
import org.apache.ibatis.jdbc.SQL;

public class ActorTypeSqlProvider {

    public String insertSelective(ActorType record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_actor_type");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getActorId() != null) {
            sql.VALUES("actor_id", "#{actorId,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            sql.VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ActorType record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_actor_type");
        
        if (record.getActorId() != null) {
            sql.SET("actor_id = #{actorId,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            sql.SET("type_id = #{typeId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}