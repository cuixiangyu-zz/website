package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.VideoSqlProvider;
import com.cxy.website.model.Video;
import com.github.pagehelper.PageInfo;
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

public interface VideoMapper {
    @Delete({
        "delete from tb_video",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_video (id, name, ",
        "video_url, ",
        "cover_url, creat_time, ",
        "creater, type, exist, ",
        "remark)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{videoUrl,jdbcType=VARCHAR}, ",
        "#{coverUrl,jdbcType=VARCHAR}, #{creatTime,jdbcType=TIMESTAMP}, ",
        "#{creater,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, #{exist,jdbcType=INTEGER}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(Video record);

    @InsertProvider(type= VideoSqlProvider.class, method="insertSelective")
    int insertSelective(Video record);

    @Select({
        "select",
        "id, name,  video_url, cover_url, creat_time, creater, type, exist, remark",
        "from tb_video",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),

        @Result(column="video_url", property="videoUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="cover_url", property="coverUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="creat_time", property="creatTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="exist", property="exist", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Video selectByPrimaryKey(Integer id);

    @UpdateProvider(type=VideoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Video record);

    @Update({
        "update tb_video",
        "set name = #{name,jdbcType=VARCHAR},",

          "video_url = #{videoUrl,jdbcType=VARCHAR},",
          "cover_url = #{coverUrl,jdbcType=VARCHAR},",
          "creat_time = #{creatTime,jdbcType=TIMESTAMP},",
          "creater = #{creater,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "exist = #{exist,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Video record);

    @Select({
            "select",
            "id, name,  video_url, cover_url, creat_time, creater, type, exist, remark",
            "from tb_video",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),

            @Result(column="video_url", property="videoUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="cover_url", property="coverUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="creat_time", property="creatTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="exist", property="exist", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Video selectByName(String name);

    @Select({
            "select",
            "vid.id, vid.name, vid.video_url, vid.cover_url, vid.creat_time, vid.creater, vid.type, vid.exist, vid.remark",
            "from tb_video vid",
            "left join tb_video_actor vidact on vid.id = vidact.video_id ",
            "left join tb_actor act on vidact.actor_id = act.id",
            "where act.id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),

            @Result(column="video_url", property="videoUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="cover_url", property="coverUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="creat_time", property="creatTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="exist", property="exist", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Video> selectByActor(int id);

    @Select({
            "select",
            "id, name,  video_url, cover_url, creat_time, creater, type, exist, remark",
            "from tb_video",
            "where type = #{type,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),

            @Result(column="video_url", property="videoUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="cover_url", property="coverUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="creat_time", property="creatTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="exist", property="exist", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Video> selectByType(int type);
}