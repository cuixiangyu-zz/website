package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.UtilSqlProvider;
import com.cxy.website.model.Util;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface UtilMapper {
    @Delete({
        "delete from tb_util",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_util (id, key, ",
        "value)",
        "values (#{id,jdbcType=INTEGER}, #{key,jdbcType=VARCHAR}, ",
        "#{value,jdbcType=VARCHAR})"
    })
    int insert(Util record);

    @InsertProvider(type= UtilSqlProvider.class, method="insertSelective")
    int insertSelective(Util record);

    @Select({
        "select",
        "id, key, value",
        "from tb_util",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="key", property="key", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR)
    })
    Util selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UtilSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Util record);

    @Update({
        "update tb_util",
        "set key = #{key,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Util record);

    @Select({
            "select",
            "id, key, value",
            "from tb_util",
            "where key = #{key,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="key", property="key", jdbcType=JdbcType.VARCHAR),
            @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR)
    })
    Util selectByKey(String key);

    @Select({
            "select",
            " util.id, util.key, util.value",
            "from tb_util util"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="key", property="key", jdbcType=JdbcType.VARCHAR),
            @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR)
    })
    List<Util> selectAll();
}