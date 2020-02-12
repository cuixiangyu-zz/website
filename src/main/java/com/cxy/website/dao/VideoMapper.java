package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.VideoSqlProvider;
import com.cxy.website.model.Picture;
import com.cxy.website.model.Video;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface VideoMapper {
    @Delete({
            "delete from tb_video",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @CacheEvict
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
    @CachePut
    int insert(Video record);

    @InsertProvider(type = VideoSqlProvider.class, method = "insertSelective")
    @CachePut
    int insertSelective(Video record);

    @Select({
            "select",
            "id, name,  video_url, cover_url, creat_time, creater, type, exist, remark",
            "from tb_video",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),

            @Result(column = "video_url", property = "videoUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "exist", property = "exist", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable(cacheNames = "selectByPrimaryKey")
    Video selectByPrimaryKey(Integer id);

    @UpdateProvider(type = VideoSqlProvider.class, method = "updateByPrimaryKeySelective")
    @CachePut
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
    @CachePut
    int updateByPrimaryKey(Video record);

    @Select({
            "select",
            "vid.id, vid.name,  vid.video_url, vid.cover_url, vid.creat_time, vid.creater, vid.type, vid.exist, vid.remark ,lv.level",
            "from tb_video vid",
            "left join (SELECT AVG(`level`) as `level`,production_id FROM `tb_level`  GROUP BY production_id) lv",
            " on vid.id = lv.production_id ",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),

            @Result(column = "video_url", property = "videoUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "exist", property = "exist", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable(cacheNames = "selectByName")
    Video selectByName(String name);

    @Select({
            "select",
            "vid.id, vid.name, vid.video_url, vid.cover_url, vid.creat_time, vid.creater, vid.type, vid.exist, vid.remark ,lv.level",
            "from tb_video vid",
            "left join tb_video_actor vidact on vid.id = vidact.video_id ",
            "left join tb_actor act on vidact.actor_id = act.id",
            "left join (SELECT AVG(`level`) as `level`,production_id FROM `tb_level`  GROUP BY production_id) lv",
            " on vid.id = lv.production_id ",
            "where act.id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),

            @Result(column = "video_url", property = "videoUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "exist", property = "exist", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable(cacheNames = "selectByActor")
    List<Video> selectByActor(int id);

    @Select({
            "select",
            "vid.id, vid.name,  vid.video_url, vid.cover_url, vid.creat_time, vid.creater, vid.type, vid.exist, vid.remark ,lv.level",
            "from tb_video vid",
            "left join (SELECT AVG(`level`) as `level`,production_id FROM `tb_level`  GROUP BY production_id) lv",
            " on vid.id = lv.production_id ",
            "where type = #{type,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),

            @Result(column = "video_url", property = "videoUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "exist", property = "exist", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable(cacheNames = "selectByType")
    List<Video> selectByType(int type);

    @Select({
            "<script>",
            "select",
            "vio.id, vio.name,  vio.video_url, vio.cover_url, vio.creat_time, vio.creater, vio.type, ",
            "vio.exist, vio.remark ,lv.level",
            "from tb_video vio",
            "left join (SELECT AVG(`level`) as `level`,production_id FROM `tb_level` where production_type = 1 GROUP BY production_id) lv",
            " on vio.id = lv.production_id ",
            "<if test='actorName !=null'>",
            " left join tb_video_actor vioact on vio.id = vioact.video_id",
            " left join tb_actor act on vioact.actor_id = act.id",
            "</if>",
            "<if test='types !=null'>",
            " left join tb_video_type viotyp on vio.id = viotyp.video_id",
            " left join tb_type typ on viotyp.type_id = typ.id",
            "</if>",
            "where 1=1 and vio.type = #{videoType,jdbcType=INTEGER}",
            "<if test='types !=null'>",
            "and typ.id in",
            "<foreach collection='types' item='type'  open='(' separator=',' close=')'>",
            "#{type}",
            "</foreach>",
            "</if>",
            "<if test='actorName !=null'>",
            "and act.chinese_name like '%${actorName}%'",
            "</if>",
            "<if test='videoName !=null'>",
            "and vio.name like '%${videoName}%'",
            "</if>",
            "GROUP BY vio.id",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),

            @Result(column = "video_url", property = "videoUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "exist", property = "exist", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable(cacheNames = "selectPageList")
    List<Video> selectPageList(String actorName, String videoName, String language, List<String> types, Integer videoType);

    @Select({
            "<script>",
            "select",
            "vid.*",
            "from tb_video vid",
            "where id in ",
            "<foreach item='item' index='index' collection='idList' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "ORDER BY FIELD( id ,",
            "<foreach item='item' index='index' collection='idList' open='' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),

            @Result(column = "video_url", property = "videoUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "exist", property = "exist", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR)
    })
    List<Video> selectByIdList(@Param("idList") List<Integer> idList);
}