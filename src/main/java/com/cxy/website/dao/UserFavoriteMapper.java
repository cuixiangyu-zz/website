package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.UserFavoriteSqlProvider;
import com.cxy.website.model.UserFavorite;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserFavoriteMapper {
    @Delete({
            "delete from tb_user_favorite",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert({
            "insert into tb_user_favorite (id, user_id, ",
            "type, video_id, creat_time)",
            "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
            "#{type,jdbcType=INTEGER}, #{videoId,jdbcType=INTEGER}, #{creatTime,jdbcType=TIMESTAMP})"
    })
    int insert(UserFavorite record);

    @InsertProvider(type = UserFavoriteSqlProvider.class, method = "insertSelective")
    int insertSelective(UserFavorite record);

    @Select({
            "select",
            "id, user_id, type, video_id, creat_time",
            "from tb_user_favorite",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "video_id", property = "videoId", jdbcType = JdbcType.INTEGER),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP)
    })
    UserFavorite selectByPrimaryKey(Integer id);

    @UpdateProvider(type = UserFavoriteSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserFavorite record);

    @Update({
            "update tb_user_favorite",
            "set user_id = #{userId,jdbcType=INTEGER},",
            "type = #{type,jdbcType=INTEGER},",
            "video_id = #{videoId,jdbcType=INTEGER},",
            "creat_time = #{creatTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserFavorite record);


    @Select({
            "<script>",
            "select",
            "favorite.*",
            "from tb_user_favorite favorite",
            "<if test='level!=null'>",
            "LEFT JOIN tb_level LEVEL ON favorite.video_id = LEVEL.production_id ",
            "</if>",
            "where favorite.user_id = #{userId,jdbcType=INTEGER}",
            "<if test='type!=null'>",
            "and favorite.type = #{type,jdbcType=INTEGER}",
            "</if>",
            "<if test='level!=null'>",
            "AND LEVEL.user_id = 1 ",
            "AND LEVEL.production_type = 2 ",
            "AND LEVEL.LEVEL = 4",
            "</if>",
            "order by favorite.creat_time desc",
            "</script>",
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "video_id", property = "videoId", jdbcType = JdbcType.INTEGER),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<UserFavorite> getList(Integer userId, Integer type, Integer level);

    @Select({
            "<script>",
            "select",
            "id, user_id, type, video_id, creat_time",
            "from tb_user_favorite",
            "where user_id = #{userId,jdbcType=INTEGER}",
            "and video_id = #{videoId,jdbcType=INTEGER}",
            "and type = #{type,jdbcType=INTEGER}",
            "order by creat_time desc",
            "</script>",
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "video_id", property = "videoId", jdbcType = JdbcType.INTEGER),
            @Result(column = "creat_time", property = "creatTime", jdbcType = JdbcType.TIMESTAMP)
    })
    UserFavorite selectByUserIdAndVideoId(Integer userId,Integer type, Integer videoId);
}