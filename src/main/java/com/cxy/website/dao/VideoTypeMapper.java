package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.VideoTypeSqlProvider;
import com.cxy.website.model.VideoType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface VideoTypeMapper {
    @Delete({
        "delete from tb_video_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_video_type (id, video_id, ",
        "type_id)",
        "values (#{id,jdbcType=INTEGER}, #{videoId,jdbcType=INTEGER}, ",
        "#{typeId,jdbcType=INTEGER})"
    })
    int insert(VideoType record);

    @InsertProvider(type= VideoTypeSqlProvider.class, method="insertSelective")
    int insertSelective(VideoType record);

    @Select({
        "select",
        "id, video_id, type_id",
        "from tb_video_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="video_id", property="videoId", jdbcType=JdbcType.INTEGER),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER)
    })
    VideoType selectByPrimaryKey(Integer id);

    @UpdateProvider(type=VideoTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VideoType record);

    @Update({
        "update tb_video_type",
        "set video_id = #{videoId,jdbcType=INTEGER},",
          "type_id = #{typeId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VideoType record);
}