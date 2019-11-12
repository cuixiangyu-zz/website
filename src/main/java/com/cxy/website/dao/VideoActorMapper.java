package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.VideoActorSqlProvider;
import com.cxy.website.model.VideoActor;
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

public interface VideoActorMapper {
    @Delete({
        "delete from tb_video_actor",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_video_actor (id, video_id, ",
        "actor_id)",
        "values (#{id,jdbcType=INTEGER}, #{videoId,jdbcType=INTEGER}, ",
        "#{actorId,jdbcType=INTEGER})"
    })
    int insert(VideoActor record);

    @InsertProvider(type= VideoActorSqlProvider.class, method="insertSelective")
    int insertSelective(VideoActor record);

    @Select({
        "select",
        "id, video_id, actor_id",
        "from tb_video_actor",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="video_id", property="videoId", jdbcType=JdbcType.INTEGER),
        @Result(column="actor_id", property="actorId", jdbcType=JdbcType.INTEGER)
    })
    VideoActor selectByPrimaryKey(Integer id);

    @UpdateProvider(type=VideoActorSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VideoActor record);

    @Update({
        "update tb_video_actor",
        "set video_id = #{videoId,jdbcType=INTEGER},",
          "actor_id = #{actorId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VideoActor record);

    @Insert({
            "<script>",
            "insert into tb_video_actor (video_id,actor_id) ",
            "select #{id},act.id from tb_actor act where act.name in ",
            "<foreach item='artist' index='index' collection='artists'",
            "  open='(' separator=',' close=')'>\n",
            "    #{artist}\n",
            "</foreach>",
            "</script>"
    })
    void updateVideoActor(Integer id, List<String> artists);
}