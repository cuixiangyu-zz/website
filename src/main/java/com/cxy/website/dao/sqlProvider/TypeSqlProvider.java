package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.Type;
import org.apache.ibatis.jdbc.SQL;

public class TypeSqlProvider {

    public String insertSelective(Type record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_type");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTypeName() != null) {
            sql.VALUES("type_name", "#{typeName,jdbcType=VARCHAR}");
        }
        
        if (record.getChineseName() != null) {
            sql.VALUES("chinese_name", "#{chineseName,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Type record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_type");
        
        if (record.getTypeName() != null) {
            sql.SET("type_name = #{typeName,jdbcType=VARCHAR}");
        }
        
        if (record.getChineseName() != null) {
            sql.SET("chinese_name = #{chineseName,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}