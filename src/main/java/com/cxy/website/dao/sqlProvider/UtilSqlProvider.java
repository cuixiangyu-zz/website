package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.Util;
import org.apache.ibatis.jdbc.SQL;

public class UtilSqlProvider {

    public String insertSelective(Util record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_util");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getKey() != null) {
            sql.VALUES("key", "#{key,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.VALUES("value", "#{value,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Util record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_util");
        
        if (record.getKey() != null) {
            sql.SET("key = #{key,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.SET("value = #{value,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}