package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.Level;
import org.apache.ibatis.jdbc.SQL;

public class LevelSqlProvider {

    public String insertSelective(Level record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_level");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getProductionId() != null) {
            sql.VALUES("production_id", "#{productionId,jdbcType=INTEGER}");
        }
        
        if (record.getLevel() != null) {
            sql.VALUES("level", "#{level,jdbcType=INTEGER}");
        }
        
        if (record.getWatchNum() != null) {
            sql.VALUES("watch_num", "#{watchNum,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Level record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_level");
        
        if (record.getProductionId() != null) {
            sql.SET("production_id = #{productionId,jdbcType=INTEGER}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{level,jdbcType=INTEGER}");
        }
        
        if (record.getWatchNum() != null) {
            sql.SET("watch_num = #{watchNum,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}