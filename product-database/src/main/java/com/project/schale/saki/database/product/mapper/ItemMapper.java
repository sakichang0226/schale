package com.project.schale.saki.database.product.mapper;

import com.project.schale.saki.database.product.model.Item;
import com.project.schale.saki.database.product.model.ItemExample;
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

public interface ItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @SelectProvider(type=ItemSqlProvider.class, method="countByExample")
    long countByExample(ItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @DeleteProvider(type=ItemSqlProvider.class, method="deleteByExample")
    int deleteByExample(ItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @Delete({
        "delete from items",
        "where item_id = #{itemId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Long itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @Insert({
        "insert into items (item_id, name, ",
        "shop_id, category_id, ",
        "price, purchase_num, ",
        "stock, is_stopped, create_at, ",
        "update_at, description)",
        "values (#{itemId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{shopId,jdbcType=INTEGER}, #{categoryId,jdbcType=INTEGER}, ",
        "#{price,jdbcType=INTEGER}, #{purchaseNum,jdbcType=INTEGER}, ",
        "#{stock,jdbcType=INTEGER}, #{isStopped,jdbcType=BIT}, #{createAt,jdbcType=TIMESTAMP}, ",
        "#{updateAt,jdbcType=TIMESTAMP}, #{description,jdbcType=LONGVARCHAR})"
    })
    int insert(Item row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @InsertProvider(type=ItemSqlProvider.class, method="insertSelective")
    int insertSelective(Item row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @SelectProvider(type=ItemSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="shop_id", property="shopId", jdbcType=JdbcType.INTEGER),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="purchase_num", property="purchaseNum", jdbcType=JdbcType.INTEGER),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="is_stopped", property="isStopped", jdbcType=JdbcType.BIT),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_at", property="updateAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Item> selectByExampleWithBLOBs(ItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @SelectProvider(type=ItemSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="shop_id", property="shopId", jdbcType=JdbcType.INTEGER),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="purchase_num", property="purchaseNum", jdbcType=JdbcType.INTEGER),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="is_stopped", property="isStopped", jdbcType=JdbcType.BIT),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_at", property="updateAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Item> selectByExample(ItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @Select({
        "select",
        "item_id, name, shop_id, category_id, price, purchase_num, stock, is_stopped, ",
        "create_at, update_at, description",
        "from items",
        "where item_id = #{itemId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="shop_id", property="shopId", jdbcType=JdbcType.INTEGER),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="purchase_num", property="purchaseNum", jdbcType=JdbcType.INTEGER),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="is_stopped", property="isStopped", jdbcType=JdbcType.BIT),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_at", property="updateAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    Item selectByPrimaryKey(Long itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("row") Item row, @Param("example") ItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("row") Item row, @Param("example") ItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("row") Item row, @Param("example") ItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @UpdateProvider(type=ItemSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Item row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @Update({
        "update items",
        "set name = #{name,jdbcType=VARCHAR},",
          "shop_id = #{shopId,jdbcType=INTEGER},",
          "category_id = #{categoryId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=INTEGER},",
          "purchase_num = #{purchaseNum,jdbcType=INTEGER},",
          "stock = #{stock,jdbcType=INTEGER},",
          "is_stopped = #{isStopped,jdbcType=BIT},",
          "create_at = #{createAt,jdbcType=TIMESTAMP},",
          "update_at = #{updateAt,jdbcType=TIMESTAMP},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where item_id = #{itemId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Item row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    @Update({
        "update items",
        "set name = #{name,jdbcType=VARCHAR},",
          "shop_id = #{shopId,jdbcType=INTEGER},",
          "category_id = #{categoryId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=INTEGER},",
          "purchase_num = #{purchaseNum,jdbcType=INTEGER},",
          "stock = #{stock,jdbcType=INTEGER},",
          "is_stopped = #{isStopped,jdbcType=BIT},",
          "create_at = #{createAt,jdbcType=TIMESTAMP},",
          "update_at = #{updateAt,jdbcType=TIMESTAMP}",
        "where item_id = #{itemId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Item row);
}