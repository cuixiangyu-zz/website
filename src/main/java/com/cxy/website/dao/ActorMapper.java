package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.ActorSqlProvider;
import com.cxy.website.model.Actor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.*;

import java.util.List;
import java.util.Map;

public interface ActorMapper {
    @Delete({
            "delete from tb_actor",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @CacheEvict
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into tb_actor (id, name, ",
            "chinese_name, contry, ",
            "birth, type, cover_url, ",
            "creat_time, creater, ",
            "remark)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{chineseName,jdbcType=VARCHAR}, #{contry,jdbcType=VARCHAR}, ",
            "#{birth,jdbcType=DATE}, #{type,jdbcType=INTEGER}, #{coverUrl,jdbcType=VARCHAR}, ",
            "#{creatTime,jdbcType=TIMESTAMP}, #{creater,jdbcType=VARCHAR}, ",
            "#{remark,jdbcType=VARCHAR})"
    })
    @CachePut
    int insert(Actor record);

    @InsertProvider(type = ActorSqlProvider.class, method = "insertSelective")
    @CachePut
    int insertSelective(Actor record);

    @Select({
            "select",
            "id, name, chinese_name, contry, birth, type, cover_url, creat_time, creater, ",
            "remark",
            "from tb_actor",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese_name", property = "chineseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contry", property = "contry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birth", property = "birth", jdbcType = JdbcType.DATE),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable
    Actor selectByPrimaryKey(Integer id);

    @UpdateProvider(type = ActorSqlProvider.class, method = "updateByPrimaryKeySelective")
    @CachePut
    int updateByPrimaryKeySelective(Actor record);

    @Update({
            "update tb_actor",
            "set name = #{name,jdbcType=VARCHAR},",
            "chinese_name = #{chineseName,jdbcType=VARCHAR},",
            "contry = #{contry,jdbcType=VARCHAR},",
            "birth = #{birth,jdbcType=DATE},",
            "type = #{type,jdbcType=INTEGER},",
            "cover_url = #{coverUrl,jdbcType=VARCHAR},",
            "creat_time = #{creatTime,jdbcType=TIMESTAMP},",
            "creater = #{creater,jdbcType=VARCHAR},",
            "remark = #{remark,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @CachePut
    int updateByPrimaryKey(Actor record);

    @Select({
            "select",
            "id, name, chinese_name, contry, birth, type, cover_url, creat_time, creater, ",
            "remark",
            "from tb_actor",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese_name", property = "chineseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contry", property = "contry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birth", property = "birth", jdbcType = JdbcType.DATE),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable
    Actor selectByName(String name);

    @Select({
            "select",
            "id, name, chinese_name, contry, birth, type, cover_url, creat_time, creater, ",
            "remark",
            "from tb_actor",
            "where chinese_name = #{chineseName,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese_name", property = "chineseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contry", property = "contry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birth", property = "birth", jdbcType = JdbcType.DATE),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable
    Actor selectByChineseName(String chineseName);

    @Select({
            "select",
            "id, name, chinese_name, contry, birth, type, cover_url, creat_time, creater, ",
            "remark",
            "from tb_actor",
            "where type = #{type,jdbcType=INTEGER} and remark >=5"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese_name", property = "chineseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contry", property = "contry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birth", property = "birth", jdbcType = JdbcType.DATE),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable
    List<Actor> selectByType(int type);

    @Select({
            "select",
            "id, name, chinese_name, contry, birth, type, cover_url, creat_time, creater, ",
            "remark",
            "from tb_actor",
            "where contry = #{country,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese_name", property = "chineseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contry", property = "contry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birth", property = "birth", jdbcType = JdbcType.DATE),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable
    List<Actor> selectByCountry(String country);

    @Select({
            "select",
            "act.id, act.name, act.chinese_name, act.contry, act.birth, act.type, act.cover_url, act.creat_time, act.creater, ",
            "act.remark",
            "from tb_actor act",
            "left join tb_level lv on act.id = lv.production_id",
            "where lv.level = #{level,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese_name", property = "chineseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contry", property = "contry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birth", property = "birth", jdbcType = JdbcType.DATE),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable
    List<Actor> selectByLevel(int level);

    @Select({
            "select",
            "act.id, act.name, act.chinese_name, act.contry, act.birth, act.type, act.cover_url, act.creat_time, act.creater, ",
            "act.remark",
            "from tb_actor act",
            "left join tb_picture_actor picact on act.id = picact.actor_id",
            "left join tb_picture pic on picact.picture_id = pic.id",
            "where pic.id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese_name", property = "chineseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contry", property = "contry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birth", property = "birth", jdbcType = JdbcType.DATE),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    @Cacheable
    List<Actor> selectByPictureid(Integer id);

    @Select({
            "select",
            "act.id, act.name, act.chinese_name, act.contry, act.birth, act.type, act.cover_url, act.creat_time, act.creater, ",
            "act.remark ,lv.level , lv.user_id as count",
            "from tb_actor act",
            "left join tb_video_actor vidact on act.id = vidact.actor_id",
            "left join tb_video vid on vidact.video_id = vid.id",
            "left join tb_level lv on act.id = lv.production_id",
            "where vid.id = #{id,jdbcType=INTEGER}",
            " and lv.production_type = 5 "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese_name", property = "chineseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contry", property = "contry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birth", property = "birth", jdbcType = JdbcType.DATE),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.INTEGER),
            @Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER)
    })
    @Cacheable
    List<Actor> selectByVideoid(Integer id);

    @Select({
            "select",
            "act.id, act.name, act.chinese_name, act.contry, act.birth, act.type, act.cover_url, act.creat_time, act.creater, ",
            "act.remark ,lv.level , lv.user_id as count",
            "from tb_actor act ",
            "left join tb_level lv on act.id = lv.production_id",
            "where remark >=5 and lv.production_type = 5"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese_name", property = "chineseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contry", property = "contry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birth", property = "birth", jdbcType = JdbcType.DATE),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creater", property = "creater", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.INTEGER),
            @Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER)
    })
    @Cacheable
    List<Actor> selectAll();

    @Select({
            "SELECT",
            "AVG( lv.level ) AS level ,(\n",
            "SELECT",
            "count( * ) ",
            "FROM",
            "tb_video vid",
            "LEFT JOIN tb_video_actor vidact ON vid.id = vidact.video_id",
            "LEFT JOIN tb_actor act ON vidact.actor_id = act.id ",
            "WHERE",
            "act.id = #{actorId,jdbcType=INTEGER} ",
            ") as count",
            "FROM",
            "tb_level lv",
            "LEFT JOIN tb_video vid ON lv.production_id = vid.id",
            "LEFT JOIN tb_video_actor vidact ON vid.id = vidact.video_id",
            "LEFT JOIN tb_actor act ON vidact.actor_id = act.id ",
            "WHERE act.id = #{actorId,jdbcType=INTEGER} and lv.production_type = 1"
    })
    @Results({
            @Result(column = "level", property = "level", jdbcType = JdbcType.INTEGER),
            @Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER)
    })
    @Cacheable
    Map<String, Object> selectActorLevel(Integer actorId);
}