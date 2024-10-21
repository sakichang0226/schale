package com.project.schale.saki.database.product.mapper;

import com.project.schale.saki.database.product.model.Shop;
import com.project.schale.saki.database.product.model.ShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ShopMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @SelectProvider(type=ShopSqlProvider.class, method="countByExample")
    long countByExample(ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @DeleteProvider(type=ShopSqlProvider.class, method="deleteByExample")
    int deleteByExample(ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @Delete({
        "delete from shops",
        "where shop_id = #{shopId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Long shopId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @Insert({
        "insert into shops (shop_id, name, ",
        "create_at, update_at)",
        "values (#{shopId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{createAt,jdbcType=TIMESTAMP}, #{updateAt,jdbcType=TIMESTAMP})"
    })
    int insert(Shop row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @InsertProvider(type=ShopSqlProvider.class, method="insertSelective")
    int insertSelective(Shop row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @SelectProvider(type=ShopSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="shop_id", property="shopId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_at", property="updateAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Shop> selectByExample(ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @Select({
        "select",
        "shop_id, name, create_at, update_at",
        "from shops",
        "where shop_id = #{shopId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="shop_id", property="shopId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_at", property="updateAt", jdbcType=JdbcType.TIMESTAMP)
    })
    Shop selectByPrimaryKey(Long shopId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @UpdateProvider(type=ShopSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("row") Shop row, @Param("example") ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @UpdateProvider(type=ShopSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("row") Shop row, @Param("example") ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @UpdateProvider(type=ShopSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Shop row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @Update({
        "update shops",
        "set name = #{name,jdbcType=VARCHAR},",
          "create_at = #{createAt,jdbcType=TIMESTAMP},",
          "update_at = #{updateAt,jdbcType=TIMESTAMP}",
        "where shop_id = #{shopId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Shop row);
}