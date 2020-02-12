package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.ViewHistorySqlProvider;
import com.cxy.website.model.ViewHistory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

public interface ViewHistoryMapper {
    @Delete({
        "delete from tb_view_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_view_history (id, type, ",
        "video_id, start_data, ",
        "watch_time, user_id)",
        "values (#{id,jdbcType=INTEGER}, #{type,jdbcType=INTEGER}, ",
        "#{videoId,jdbcType=INTEGER}, #{startData,jdbcType=TIMESTAMP}, ",
        "#{watchTime,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER})"
    })
    int insert(ViewHistory record);

    @InsertProvider(type= ViewHistorySqlProvider.class, method="insertSelective")
    int insertSelective(ViewHistory record);

    @Select({
        "select",
        "id, type, video_id, start_data, watch_time, user_id",
        "from tb_view_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="video_id", property="videoId", jdbcType=JdbcType.INTEGER),
        @Result(column="start_data", property="startData", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="watch_time", property="watchTime", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    ViewHistory selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ViewHistorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ViewHistory record);

    @Update({
        "update tb_view_history",
        "set type = #{type,jdbcType=INTEGER},",
          "video_id = #{videoId,jdbcType=INTEGER},",
          "start_data = #{startData,jdbcType=TIMESTAMP},",
          "watch_time = #{watchTime,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ViewHistory record);

    @Select({
            "<script>",
            "select",
            "id, type, video_id, start_data, watch_time, user_id",
            "from tb_view_history",
            "where user_id = #{userId,jdbcType=INTEGER}",
            "and 1 = 1",
            "<if test='type !=null'>",
            "and type = #{type,jdbcType=INTEGER}",
            "</if>",
            "<if test='startTime !=null'>",
            "and start_data &gt;= #{startTime,jdbcType=TIMESTAMP}",
            "</if>",
            "<if test='endTime !=null'>",
            "and start_data &lt;= #{endTime,jdbcType=TIMESTAMP}",
            "</if>",
            "order by start_data desc",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="video_id", property="videoId", jdbcType=JdbcType.INTEGER),
            @Result(column="start_data", property="startData", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="watch_time", property="watchTime", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<ViewHistory> getHistory(int userId, Integer type, String startTime, String endTime);
}