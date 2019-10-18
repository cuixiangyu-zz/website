package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.Actor;
import org.apache.ibatis.jdbc.SQL;

public class ActorSqlProvider {

    public String insertSelective(Actor record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_actor");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getChineseName() != null) {
            sql.VALUES("chinese_name", "#{chineseName,jdbcType=VARCHAR}");
        }
        
        if (record.getContry() != null) {
            sql.VALUES("contry", "#{contry,jdbcType=VARCHAR}");
        }
        
        if (record.getBirth() != null) {
            sql.VALUES("birth", "#{birth,jdbcType=DATE}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
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
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Actor record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_actor");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getChineseName() != null) {
            sql.SET("chinese_name = #{chineseName,jdbcType=VARCHAR}");
        }
        
        if (record.getContry() != null) {
            sql.SET("contry = #{contry,jdbcType=VARCHAR}");
        }
        
        if (record.getBirth() != null) {
            sql.SET("birth = #{birth,jdbcType=DATE}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
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
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}