package com.cxy.website.dao.sqlProvider;

import com.cxy.website.model.Picture;
import org.apache.ibatis.jdbc.SQL;

public class PictureSqlProvider {

    public String insertSelective(Picture record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_picture");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getPictureUrl() != null) {
            sql.VALUES("picture_url", "#{pictureUrl,jdbcType=VARCHAR}");
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
        
        if (record.getNumber() != null) {
            sql.VALUES("number", "#{number,jdbcType=INTEGER}");
        }
        
        if (record.getLanguage() != null) {
            sql.VALUES("language", "#{language,jdbcType=VARCHAR}");
        }
        
        if (record.getExist() != null) {
            sql.VALUES("exist", "#{exist,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Picture record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_picture");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getPictureUrl() != null) {
            sql.SET("picture_url = #{pictureUrl,jdbcType=VARCHAR}");
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
        
        if (record.getNumber() != null) {
            sql.SET("number = #{number,jdbcType=INTEGER}");
        }
        
        if (record.getLanguage() != null) {
            sql.SET("language = #{language,jdbcType=VARCHAR}");
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