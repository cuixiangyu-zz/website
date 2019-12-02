package com.cxy.website.dao;

import com.cxy.website.dao.sqlProvider.ActorTypeSqlProvider;
import com.cxy.website.model.ActorType;
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

public interface ActorTypeMapper {
    @Delete({
        "delete from tb_actor_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @CacheEvict
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_actor_type (id, actor_id, ",
        "type_id)",
        "values (#{id,jdbcType=INTEGER}, #{actorId,jdbcType=INTEGER}, ",
        "#{typeId,jdbcType=INTEGER})"
    })
    @CachePut
    int insert(ActorType record);

    @InsertProvider(type= ActorTypeSqlProvider.class, method="insertSelective")
    @CachePut
    int insertSelective(ActorType record);

    @Select({
        "select",
        "id, actor_id, type_id",
        "from tb_actor_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="actor_id", property="actorId", jdbcType=JdbcType.INTEGER),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER)
    })
    @Cacheable
    ActorType selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ActorTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    @CachePut
    int updateByPrimaryKeySelective(ActorType record);

    @Update({
        "update tb_actor_type",
        "set actor_id = #{actorId,jdbcType=INTEGER},",
          "type_id = #{typeId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @CachePut
    int updateByPrimaryKey(ActorType record);
}