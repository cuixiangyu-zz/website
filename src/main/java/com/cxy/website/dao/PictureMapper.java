package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.PictureSqlProvider;
import com.cxy.website.model.Picture;
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

public interface PictureMapper {
    @Delete({
        "delete from tb_picture",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_picture (id, name, ",
        " picture_url, ",
        "cover_url, creat_time, ",
        "creater, type, number, ",
        "language, exist, ",
        "remark)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        " #{pictureUrl,jdbcType=VARCHAR}, ",
        "#{coverUrl,jdbcType=VARCHAR}, #{creatTime,jdbcType=TIMESTAMP}, ",
        "#{creater,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, #{number,jdbcType=INTEGER}, ",
        "#{language,jdbcType=VARCHAR}, #{exist,jdbcType=INTEGER}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(Picture record);

    @InsertProvider(type= PictureSqlProvider.class, method="insertSelective")
    int insertSelective(Picture record);

    @Select({
        "select",
        "id, name,  picture_url, cover_url, creat_time, creater, type, number, ",
        "language, exist, remark",
        "from tb_picture",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),

        @Result(column="picture_url", property="pictureUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="cover_url", property="coverUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="creat_time", property="creatTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
        @Result(column="language", property="language", jdbcType=JdbcType.VARCHAR),
        @Result(column="exist", property="exist", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Picture selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PictureSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Picture record);

    @Update({
        "update tb_picture",
        "set name = #{name,jdbcType=VARCHAR},",

          "picture_url = #{pictureUrl,jdbcType=VARCHAR},",
          "cover_url = #{coverUrl,jdbcType=VARCHAR},",
          "creat_time = #{creatTime,jdbcType=TIMESTAMP},",
          "creater = #{creater,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "number = #{number,jdbcType=INTEGER},",
          "language = #{language,jdbcType=VARCHAR},",
          "exist = #{exist,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Picture record);

    @Select({
            "select",
            "id, name,  picture_url, cover_url, creat_time, creater, type, number, ",
            "language, exist, remark",
            "from tb_picture",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),

            @Result(column="picture_url", property="pictureUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="cover_url", property="coverUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="creat_time", property="creatTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="language", property="language", jdbcType=JdbcType.VARCHAR),
            @Result(column="exist", property="exist", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Picture selectByName(String name);

    @Select({
            "select",
            "id, name,  picture_url, cover_url, creat_time, creater, type, number, ",
            "language, exist, remark",
            "from tb_picture pic",
            "left join tb_picture_actor picact on pic.id = picact.picture_id ",
            "left join tb_actor act on picact.actor_id = act.id",
            "where act.id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="picture_url", property="pictureUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="cover_url", property="coverUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="creat_time", property="creatTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="language", property="language", jdbcType=JdbcType.VARCHAR),
            @Result(column="exist", property="exist", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Picture> selectByArtist(int id);

    @Select({
            "select",
            "id, name,  picture_url, cover_url, creat_time, creater, type, number, ",
            "language, exist, remark",
            "from tb_picture",
            "where type = #{type,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),

            @Result(column="picture_url", property="pictureUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="cover_url", property="coverUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="creat_time", property="creatTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="language", property="language", jdbcType=JdbcType.VARCHAR),
            @Result(column="exist", property="exist", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Picture> selectByType(int type);

    @Select({
            "select",
            "id, name,  picture_url, cover_url, creat_time, creater, type, number, ",
            "language, exist, remark",
            "from tb_picture",
            "where language = #{language,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),

            @Result(column="picture_url", property="pictureUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="cover_url", property="coverUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="creat_time", property="creatTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="language", property="language", jdbcType=JdbcType.VARCHAR),
            @Result(column="exist", property="exist", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Picture> selectByLanguage(String language);
}