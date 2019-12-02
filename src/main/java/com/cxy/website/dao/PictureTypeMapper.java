package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.PictureTypeSqlProvider;
import com.cxy.website.model.PictureType;
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

public interface PictureTypeMapper {
    @Delete({
        "delete from tb_picture_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @CacheEvict(value="PictureType")
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_picture_type (id, picture_id, ",
        "type_id)",
        "values (#{id,jdbcType=INTEGER}, #{pictureId,jdbcType=INTEGER}, ",
        "#{typeId,jdbcType=INTEGER})"
    })
    @CachePut
    int insert(PictureType record);

    @InsertProvider(type= PictureTypeSqlProvider.class, method="insertSelective")
    @CachePut
    int insertSelective(PictureType record);

    @Select({
        "select",
        "id, picture_id, type_id",
        "from tb_picture_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="picture_id", property="pictureId", jdbcType=JdbcType.INTEGER),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER)
    })
    @Cacheable(value="PictureType")
    PictureType selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PictureTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    @CachePut
    int updateByPrimaryKeySelective(PictureType record);

    @Update({
        "update tb_picture_type",
        "set picture_id = #{pictureId,jdbcType=INTEGER},",
          "type_id = #{typeId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @CachePut
    int updateByPrimaryKey(PictureType record);

    @Delete({
            "delete from tb_picture_type",
            "where picture_id = #{id,jdbcType=INTEGER}"
    })
    @CacheEvict(value="PictureType")
    int deleteByPictureId(int id);
}