package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.PictureType;
import org.apache.ibatis.jdbc.SQL;

public class PictureTypeSqlProvider {

    public String insertSelective(PictureType record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_picture_type");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getPictureId() != null) {
            sql.VALUES("picture_id", "#{pictureId,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            sql.VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PictureType record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_picture_type");
        
        if (record.getPictureId() != null) {
            sql.SET("picture_id = #{pictureId,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            sql.SET("type_id = #{typeId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}