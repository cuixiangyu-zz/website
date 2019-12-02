package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.TypeSqlProvider;
import com.cxy.website.model.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface TypeMapper {
    @Delete({
        "delete from tb_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @CacheEvict
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_type (id, type_name, ",
        "chinese_name, type)",
        "values (#{id,jdbcType=INTEGER}, #{typeName,jdbcType=VARCHAR}, ",
        "#{chineseName,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER})"
    })
    @CachePut
    int insert(Type record);

    @InsertProvider(type= TypeSqlProvider.class, method="insertSelective")
    @CachePut
    int insertSelective(Type record);

    @Select({
        "select",
        "id, type_name, chinese_name, type",
        "from tb_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_name", property="typeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="chinese_name", property="chineseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    @Cacheable
    Type selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TypeSqlProvider.class, method="updateByPrimaryKeySelective")
    @CachePut
    int updateByPrimaryKeySelective(Type record);

    @Update({
        "update tb_type",
        "set type_name = #{typeName,jdbcType=VARCHAR},",
          "chinese_name = #{chineseName,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @CachePut
    int updateByPrimaryKey(Type record);

    @Select({
            "select",
            "id, type_name, chinese_name, type",
            "from tb_type",
            "where type_name = #{name,jdbcType=VARCHAR}",
            "and type = #{type,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_name", property="typeName", jdbcType=JdbcType.VARCHAR),
            @Result(column="chinese_name", property="chineseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    @Cacheable
    Type selectByName(String name,Integer type);

    @Select({
            "select",
            "id, type_name, chinese_name, type",
            "from tb_type",
            "where chinese_name = #{chinesename,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_name", property="typeName", jdbcType=JdbcType.VARCHAR),
            @Result(column="chinese_name", property="chineseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    @Cacheable
    Type selectByChineseName(String chinesename);

    @Select({
            "select",
            "typ.id, typ.type_name, typ.chinese_name, typ.type",
            "from tb_type typ",
            "left join tb_picture_type pictyp on typ.id = pictyp.type_id",
            "left join tb_picture pic on pictyp.picture_id = pic.id",
            "where pic.id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_name", property="typeName", jdbcType=JdbcType.VARCHAR),
            @Result(column="chinese_name", property="chineseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    @Cacheable
    List<Type> selectByPictureid(Integer id);

    @Select({
            "select",
            "typ.id, typ.type_name, typ.chinese_name, typ.type",
            "from tb_type typ",
            "left join tb_video_type vidtyp on typ.id = vidtyp.type_id",
            "left join tb_video vid on vidtyp.video_id = vid.id",
            "where vid.id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_name", property="typeName", jdbcType=JdbcType.VARCHAR),
            @Result(column="chinese_name", property="chineseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    @Cacheable
    List<Type> selectByVideoId(Integer id);

    @Select({
            "select",
            "typ.id, typ.type_name, typ.chinese_name, typ.type",
            "from tb_type typ",
            "where typ.type = #{type,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type_name", property="typeName", jdbcType=JdbcType.VARCHAR),
            @Result(column="chinese_name", property="chineseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    @Cacheable
    List<Type> selectByType(int type);

}