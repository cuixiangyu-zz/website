package com.cxy.website.dao.sys;

import com.cxy.website.dao.sys.sqlprovider.SysMenuSqlProvider;
import com.cxy.website.model.sys.SysMenu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_sys_menu
     *
     * @mbg.generated
     */
    @Delete({
        "delete from tb_sys_menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_sys_menu
     *
     * @mbg.generated
     */
    @Insert({
        "insert into tb_sys_menu (id, pid, ",
        "name, PERMISSION, ",
        "order_no, intent, ",
        "menu_url, description, ",
        "menu_level, is_valid, ",
        "img_url, create_time, ",
        "creater, remark)",
        "values (#{id,jdbcType=INTEGER}, #{pid,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{permission,jdbcType=VARCHAR}, ",
        "#{orderNo,jdbcType=INTEGER}, ",
        "#{menuUrl,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{menuLevel,jdbcType=VARCHAR}, #{isValid,jdbcType=INTEGER}, ",
        "#{imgUrl,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{creater,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_sys_menu
     *
     * @mbg.generated
     */
    @InsertProvider(type= SysMenuSqlProvider.class, method="insertSelective")
    int insertSelective(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_sys_menu
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, pid, name, PERMISSION, order_no, menu_url, description, menu_level, ",
        "is_valid, img_url, create_time, creater, remark",
        "from tb_sys_menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="PERMISSION", property="permission", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.INTEGER),
        @Result(column="menu_url", property="menuUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="menu_level", property="menuLevel", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_valid", property="isValid", jdbcType=JdbcType.INTEGER),
        @Result(column="img_url", property="imgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creater", property="creater", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    SysMenu selectByPrimaryKey(Integer id);
    @Select( {"<script>" +
            "select * from tb_sys_menu where id in " +
            "<foreach item = 'item' index = 'index' collection = 'list' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach> order by order_no "+
            "</script>"})
    List<SysMenu> findUserMenuList(@Param("list") List<Integer> list);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_sys_menu
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysMenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_sys_menu
     *
     * @mbg.generated
     */
    @Update({
        "update tb_sys_menu",
        "set pid = #{pid,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "PERMISSION = #{permission,jdbcType=VARCHAR},",
          "order_no = #{orderNo,jdbcType=INTEGER},",
          "menu_url = #{menuUrl,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "menu_level = #{menuLevel,jdbcType=VARCHAR},",
          "is_valid = #{isValid,jdbcType=INTEGER},",
          "img_url = #{imgUrl,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "creater = #{creater,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysMenu record);
    @Select("select * from tb_sys_menu")
    List<SysMenu> getAllMenu();
}