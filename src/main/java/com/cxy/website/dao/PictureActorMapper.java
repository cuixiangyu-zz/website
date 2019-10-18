package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.PictureActorSqlProvider;
import com.cxy.website.model.PictureActor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PictureActorMapper {
    @Delete({
        "delete from tb_picture_actor",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_picture_actor (id, picture_id, ",
        "actor_id)",
        "values (#{id,jdbcType=INTEGER}, #{pictureId,jdbcType=INTEGER}, ",
        "#{actorId,jdbcType=INTEGER})"
    })
    int insert(PictureActor record);

    @InsertProvider(type= PictureActorSqlProvider.class, method="insertSelective")
    int insertSelective(PictureActor record);

    @Select({
        "select",
        "id, picture_id, actor_id",
        "from tb_picture_actor",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="picture_id", property="pictureId", jdbcType=JdbcType.INTEGER),
        @Result(column="actor_id", property="actorId", jdbcType=JdbcType.INTEGER)
    })
    PictureActor selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PictureActorSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PictureActor record);

    @Update({
        "update tb_picture_actor",
        "set picture_id = #{pictureId,jdbcType=INTEGER},",
          "actor_id = #{actorId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PictureActor record);
}