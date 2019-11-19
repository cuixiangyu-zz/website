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
            "level, user_id , production_type)",
            "values (#{id,jdbcType=INTEGER}, #{productionId,jdbcType=INTEGER}, ",
            "#{level,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, #{productionType,jdbcType=INTEGER})"
    })
    int insert(Level record);

    @InsertProvider(type = LevelSqlProvider.class, method = "insertSelective")
    int insertSelective(Level record);

    @Select({
            "select",
            "id, production_id, level, user_id,production_type",
            "from tb_level",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "production_id", property = "productionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "level", property = "level", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "production_type", property = "productionType", jdbcType = JdbcType.INTEGER)
    })
    Level selectByPrimaryKey(Integer id);

    @UpdateProvider(type = LevelSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Level record);

    @Update({
            "update tb_level",
            "set production_id = #{productionId,jdbcType=INTEGER},",
            "level = #{level,jdbcType=INTEGER},",
            "user_id = #{userId,jdbcType=INTEGER} ,",
            "production_type = #{productionType,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Level record);

    @Select({
            "select",
            "id, production_id, level, user_id,production_type",
            "from tb_level",
            "where production_id = #{productionId,jdbcType=INTEGER}",
            "and production_type = #{productionType,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "production_id", property = "productionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "level", property = "level", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "production_type", property = "productionType", jdbcType = JdbcType.INTEGER)
    })
    List<Level> selectByProductionIdandType(int productionId,int productionType);

    @Select({
            "select",
            "id, production_id, level, user_id,production_type",
            "from tb_level",
            "where level = #{level,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "production_id", property = "productionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "level", property = "level", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "production_type", property = "productionType", jdbcType = JdbcType.INTEGER)
    })
    List<Level> selectByLevel(int level);

    @Select({
            "select",
            "id, production_id, level, user_id,production_type",
            "from tb_level",
            "where production_id = #{id,jdbcType=INTEGER}",
            "and user_id = #{userId,jdbcType=INTEGER}",
            "and production_type = #{type,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "production_id", property = "productionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "level", property = "level", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "production_type", property = "productionType", jdbcType = JdbcType.INTEGER)
    })
    Level findByProductionIdAndUserId(Integer id, Integer userId, Integer type);
}