package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.LevelSqlProvider;
import com.cxy.website.model.Level;
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

public interface LevelMapper {
    @Delete({
        "delete from tb_level",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_level (id, production_id, ",
        "level, watch_num)",
        "values (#{id,jdbcType=INTEGER}, #{productionId,jdbcType=INTEGER}, ",
        "#{level,jdbcType=INTEGER}, #{watchNum,jdbcType=INTEGER})"
    })
    int insert(Level record);

    @InsertProvider(type= LevelSqlProvider.class, method="insertSelective")
    int insertSelective(Level record);

    @Select({
        "select",
        "id, production_id, level, watch_num",
        "from tb_level",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="production_id", property="productionId", jdbcType=JdbcType.INTEGER),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="watch_num", property="watchNum", jdbcType=JdbcType.INTEGER)
    })
    Level selectByPrimaryKey(Integer id);

    @UpdateProvider(type=LevelSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Level record);

    @Update({
        "update tb_level",
        "set production_id = #{productionId,jdbcType=INTEGER},",
          "level = #{level,jdbcType=INTEGER},",
          "watch_num = #{watchNum,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Level record);

    @Select({
            "select",
            "id, production_id, level, watch_num",
            "from tb_level",
            "where production_id = #{productionId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="production_id", property="productionId", jdbcType=JdbcType.INTEGER),
            @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
            @Result(column="watch_num", property="watchNum", jdbcType=JdbcType.INTEGER)
    })
    List<Level> selectByProductionId(int productionId);

    @Select({
            "select",
            "id, production_id, level, watch_num",
            "from tb_level",
            "where level = #{level,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="production_id", property="productionId", jdbcType=JdbcType.INTEGER),
            @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
            @Result(column="watch_num", property="watchNum", jdbcType=JdbcType.INTEGER)
    })
    List<Level> selectByLevel(int level);
}